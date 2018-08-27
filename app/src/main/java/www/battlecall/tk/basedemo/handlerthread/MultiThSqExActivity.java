package www.battlecall.tk.basedemo.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import www.battlecall.tk.basedemo.R;

public class MultiThSqExActivity extends AppCompatActivity {

	HandlerThread handlerThread = new HandlerThread("test-");
	Handler myHander;
	private static final int OP1 = 1;
	private static final int OP2 = 2;
	private static final int OP3 = 3;
	private static final int OP4 = 4;
	private static final int OP5 = 5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_th_sq_ex);
		handlerThread.start();
		myHander = new MyHandler(handlerThread.getLooper());
		Message msg = myHander.obtainMessage();
		msg.what = OP1;
		myHander.sendMessage(msg);
		Message msg2 = myHander.obtainMessage();
		msg2.what = OP2;
		myHander.sendMessage(msg2);
		Message msg3 = myHander.obtainMessage();
		msg3.what = OP3;
		myHander.sendMessage(msg3);
		Message msg4 = myHander.obtainMessage();
		msg4.what = OP4;
		myHander.sendMessage(msg4);
		Message msg5 = myHander.obtainMessage();
		msg5.what = OP5;
		myHander.sendMessage(msg5);

	}

	private static final class MyHandler extends Handler{

		MyHandler(Looper looper){
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case OP1:
					Log.d("cjl", "MultiThSqExActivity ---------handleMessage:      op1");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case OP2:
					Log.d("cjl", "MultiThSqExActivity ---------handleMessage:      op2");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case OP3:
					Log.d("cjl", "MultiThSqExActivity ---------handleMessage:      op3");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case OP4:
					Log.d("cjl", "MultiThSqExActivity ---------handleMessage:      op4");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case OP5:
					Log.d("cjl", "MultiThSqExActivity ---------handleMessage:      op5");
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;

			}
		}
	}
}
