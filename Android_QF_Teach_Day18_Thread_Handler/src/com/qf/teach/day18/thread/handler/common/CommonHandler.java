package com.qf.teach.day18.thread.handler.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class CommonHandler extends Handler {
	private ProgressDialog progressDialog;
	private ICommonHandlerCallback callback;
	
	public CommonHandler(final Context context, final ICommonHandlerCallback callback) {
		this.callback = callback;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// 使用Looper将不同线程放入同一个线程队列
						Looper.prepare();
						progressDialog = new ProgressDialog(context);
						progressDialog.setMessage("数据加载中...");
						progressDialog.show();
						Looper.loop();
					}
				}).start();
				
				// 执行回调函数，应变不同的业务
				callback.doInBackground();
			}
		}).start();
	}
	
	/**
	 * 操作UI线程
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			callback.onPostExecute();
			break;
		default:
			break;
		}
		progressDialog.dismiss();
	}
}
