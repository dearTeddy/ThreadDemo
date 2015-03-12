package com.qf.teach.day18.thread.handler.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class MyHandler<Params, Progress, Result> {
	private Result result;
	
	public void onPreExecute() {}
	abstract public Result doInBackground(Params... params);
	public void onPostExecute(Result result) {}
	
	public void execute(final Params... params) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Looper.prepare();
						onPreExecute();
						Looper.loop();
					}
				}).start();
				
				result = doInBackground(params);
				// 通过系统获取Message对象
				Message msg = handler.obtainMessage();
				msg.what = 0;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		}).start();
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Result result = (Result) msg.obj;
			onPostExecute(result);
		};
	};
}
