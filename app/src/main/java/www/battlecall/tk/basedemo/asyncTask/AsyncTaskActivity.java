package www.battlecall.tk.basedemo.asyncTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import www.battlecall.tk.basedemo.R;

public class AsyncTaskActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);

		/*private final AtomicBoolean mB = new AtomicBoolean();

		mB.set(true);
		mB.getAndSet(false);*/

		final MyAsyncTask myAsyncTask = new MyAsyncTask();
//		myAsyncTask.onPreExecute();
		myAsyncTask.execute("");

		onLowMemory();
//		myAsyncTask.execute("ss");Cannot execute task: the task is already running.
		/*
		task已经运行完成
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);

					Log.d("cjl", "AsyncTaskActivity ---------run:      "+myAsyncTask.cancel(true));
					01-01 11:19:14.870 22103-24117/www.battlecall.tk.basedemo D/cjl: AsyncTaskActivity ---------run:      false

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		*/

	}


	private static class MyAsyncTask extends AsyncTask<String,Integer,String>{

		public MyAsyncTask() {
			super();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d("cjl", "MyAsyncTask ---------onPreExecute:      ");
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			Log.d("cjl", "MyAsyncTask ---------onPostExecute:      ");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			for (Integer value:values){
				value.intValue();
				Log.d("cjl", "MyAsyncTask ---------onProgressUpdate:     value "+value);
			}

		}

		@Override
		protected void onCancelled(String s) {
			super.onCancelled(s);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("cjl", "MyAsyncTask ---------doInBackground:      ");
			int c = 0;
//			while (true){
//				if (isCancelled()){
//					return "cancelled";
//				}
				publishProgress(c++);
//			}
			return "";
		}
	}
}
