package com.qf.teach.day18.thread.handler.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;

import com.qf.teach.day18.thread.handler.R;
import com.qf.teach.day18.thread.handler.adapter.TuanAdapter;
import com.qf.teach.day18.thread.handler.common.CommonJSONHelper;
import com.qf.teach.day18.thread.handler.entity.Tuan;

public class TuanActivity extends Activity {
	private static final String TAG = "TuanActivity";
	private ListView lvTuan;
	
	private List<Tuan> tuanList;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuan);

		initUI();
		initData();
	}

	private void initUI() {
		lvTuan = (ListView) findViewById(R.id.lv_tuan);
	}

	private void initData() {
		// 开启工作线程，请求网络
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 新建线程，相当于在后台任务执行前的准备工作
				new Thread(new Runnable() {
					@Override
					public void run() {
						Looper.prepare();
						progressDialog = new ProgressDialog(TuanActivity.this);
						progressDialog.setMessage("数据加载中...");
						progressDialog.show();
						Looper.loop();
					}
				}).start();
				
				String json = CommonJSONHelper.getJson("http://app.nuomi.com/naserver/search/likeitem?logpage=Home&cityid=300210000&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1410866512845&swidth=480&sheight=728&net=wifi&sign=d6dc9a24e2a74a0750443bbc19acfe1a");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				try {
					JSONObject jsonObject = new JSONObject(json);
					JSONObject jsonData = jsonObject.getJSONObject("data");
					JSONArray arrayTuanList = jsonData.getJSONArray("tuan_list");
					
					if (arrayTuanList != null && arrayTuanList.length() > 0) {
						Tuan tuan = null;
						tuanList = new ArrayList<Tuan>();
						JSONObject object = null;
						for (int i = 0 ; i < arrayTuanList.length() ; i++) {
							object = arrayTuanList.getJSONObject(i);
							tuan = new Tuan();
							tuan.setBrand_name(object.getString("brand_name"));
							tuanList.add(tuan);
						}
						
						// 调用Handler操作UI线程
						handler.sendEmptyMessage(0);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private Handler handler = new Handler() {
		// 用于操作UI线程
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				TuanAdapter adapter = new TuanAdapter(TuanActivity.this, tuanList);
				lvTuan.setAdapter(adapter);
				
				progressDialog.dismiss();
				break;
			default:
				break;
			}
		}
	};
}
