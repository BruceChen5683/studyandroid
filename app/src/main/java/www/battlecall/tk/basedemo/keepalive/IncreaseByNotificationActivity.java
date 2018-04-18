package www.battlecall.tk.basedemo.keepalive;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import www.battlecall.tk.basedemo.R;

public class IncreaseByNotificationActivity extends AppCompatActivity {

	private final static String TAG = IncreaseByNotificationActivity.class.getSimpleName();
	private InnerReceiver innerReceiver;
//	private Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			Toast.makeText(IncreaseByNotificationActivity.this,"----------- "+System.currentTimeMillis(),Toast.LENGTH_SHORT).show();
//		}
//	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_increase_priority);

		innerReceiver = new InnerReceiver(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		intentFilter.addAction(Intent.ACTION_SCREEN_ON);
		intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(innerReceiver,intentFilter);

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (1==1){
//
//					Log.d(TAG, "run: "+System.currentTimeMillis());
////					Log.d("", "run: Process.myPid() "+Process.myPid());
////					Log.d("", "run: -----"+Utils.getProcessOomAdj(Process.myPid()));
////					handler.sendEmptyMessage(0);
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("", "onDestroy: ");
		unregisterReceiver(innerReceiver);
	}
}
