package www.battlecall.tk.basedemo.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import www.battlecall.tk.basedemo.R;
import www.battlecall.tk.basedemo.service.MyService;

public class HandlerActivity extends AppCompatActivity {


//	private static final Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//		}
//	};

	private static class MyHandler extends Handler{
		private final WeakReference<HandlerActivity> mActivity;


		private MyHandler(HandlerActivity activity) {
			mActivity = new WeakReference<HandlerActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			HandlerActivity activity = mActivity.get();
			if (activity != null){

			}
		}
	}


	private final MyHandler handler = new MyHandler(this);

	private static final Runnable runable = new Runnable() {
		@Override
		public void run() {

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler);

		handler.postDelayed(runable,60*1000);

		finish();
	}
}
