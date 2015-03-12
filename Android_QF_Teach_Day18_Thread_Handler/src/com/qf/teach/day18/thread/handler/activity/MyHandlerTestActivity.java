package com.qf.teach.day18.thread.handler.activity;

import com.qf.teach.day18.thread.handler.R;
import com.qf.teach.day18.thread.handler.common.MyHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyHandlerTestActivity extends Activity {
	private static final String TAG = "MyHandlerTestActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuan);
		
		MyTask task = new MyTask();
		task.execute("Hello Execute");
	}
	
	class MyTask extends MyHandler<String, Void, String> {
		private ProgressDialog progressDialog;
		
		@Override
		public void onPreExecute() {
			progressDialog = new ProgressDialog(MyHandlerTestActivity.this);
			progressDialog.setMessage("数据加载中...");
			progressDialog.show();
		}
		
		@Override
		public String doInBackground(String... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Log.i(TAG, params[0]);
			return "Hello MyDoInBackground";
		}
		
		@Override
		public void onPostExecute(String result) {
			Toast.makeText(MyHandlerTestActivity.this, result, Toast.LENGTH_LONG).show();
			progressDialog.dismiss();
		}
		
	}
}
