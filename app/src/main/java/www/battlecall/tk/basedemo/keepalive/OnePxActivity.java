package www.battlecall.tk.basedemo.keepalive;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import www.battlecall.tk.basedemo.R;

public class OnePxActivity extends AppCompatActivity {

	private InnerReceiver innerReceiver;
//	private Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			Toast.makeText(OnePxActivity.this,"----------- "+System.currentTimeMillis(),Toast.LENGTH_SHORT).show();
//		}
//	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_px);

		Log.d("OnePxActivity", "onCreate: ");

		innerReceiver = new InnerReceiver(this);
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		registerReceiver(innerReceiver,intentFilter);

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (1==1){
//
////					Log.d("OnePxActivity", "run: "+System.currentTimeMillis());
//					Log.d("", "run: Process.myPid() "+Process.myPid());
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
