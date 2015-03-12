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
						// ʹ��Looper����ͬ�̷߳���ͬһ���̶߳���
						Looper.prepare();
						progressDialog = new ProgressDialog(context);
						progressDialog.setMessage("���ݼ�����...");
						progressDialog.show();
						Looper.loop();
					}
				}).start();
				
				// ִ�лص�������Ӧ�䲻ͬ��ҵ��
				callback.doInBackground();
			}
		}).start();
	}
	
	/**
	 * ����UI�߳�
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
