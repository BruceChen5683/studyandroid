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

	OpHandlerThread handlerThread = new OpHandlerThread("myHandlerThread");
	private MyHandler myHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hander_thread);

		Log.d("cjl", "HanderThreadActivity ---------onCreate:      ");
		myHandler = new MyHandler();
		handlerThread.setUiHandler(myHandler);
		handlerThread.start();
		//TODO
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		handlerThread.op1();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		myHandler = null;TODO  or not
		handlerThread.setUiHandler(null);
		handlerThread.quit();
		handlerThread = null;
	}

	private static class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			Log.d("cjl", "MyHandler ---------handleMessage:      "+msg.what);
		}
	}
}
