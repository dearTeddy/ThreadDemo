package com.qf.teach.day18.thread.handler.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.qf.teach.day18.thread.handler.R;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnTuan;
	private Button btnTuanHandler;
	private Button btnHandlerTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initUI();
	}

	private void initUI() {
		btnTuan = (Button) findViewById(R.id.btn_tuan);
		btnTuan.setOnClickListener(this);
		
		btnTuanHandler = (Button) findViewById(R.id.btn_tuan_handler);
		btnTuanHandler.setOnClickListener(this);
		
		btnHandlerTest = (Button) findViewById(R.id.btn_handler_test);
		btnHandlerTest.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tuan:
			startActivity(new Intent(this, TuanActivity.class));
			break;
		case R.id.btn_tuan_handler:
			startActivity(new Intent(this, TuanHandlerActivity.class));
			break;
		case R.id.btn_handler_test:
			startActivity(new Intent(this, MyHandlerTestActivity.class));
			break;
		default:
			break;
		}
	}

}
