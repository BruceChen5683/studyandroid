package www.battlecall.tk.basedemo.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import www.battlecall.tk.basedemo.R;
import www.battlecall.tk.basedemo.service.MyService;

public class HanderThreadActivity extends AppCompatActivity {

	HandlerThread handlerThread = new HandlerThread("myHandlerThread");
	private MyHandler myHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hander_thread);
		handlerThread.start();
		myHandler = new MyHandler(handlerThread.getLooper());
		myHandler.sendEmptyMessage(1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		myHandler.removeMessages(1);
		myHandler = null;
		handlerThread.quit();
		handlerThread = null;
	}

	private static class MyHandler extends Handler {

		public MyHandler(Looper looper){
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			Log.d("cjl", "MyHandler ---------handleMessage:      "+msg.what);
		}
	}
}
