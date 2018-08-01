package www.battlecall.tk.basedemo.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class BroadCastActivity extends AppCompatActivity {

	private Button btn;
	LocalReceiver localReceiver = new LocalReceiver();
	LocalReceiver2 localReceiver2 = new LocalReceiver2();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intercept);



		btn = findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Log.d("cjl", "BroadCastActivity ---------onClick:      ");

				Intent intent = new Intent("cjl.broadcast.test");
				intent.putExtra("msg","first msg");
				sendOrderedBroadcast(intent,"cjl.permission.test");
			}
		});


		findViewById(R.id.btnLocal).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				IntentFilter intentFilter = new IntentFilter();
				LocalBroadcastManager localBM = LocalBroadcastManager.getInstance(BroadCastActivity.this);
				intentFilter.addAction("cjl.broadcast.test");
				localBM.registerReceiver(localReceiver2,intentFilter);

				localBM.registerReceiver(localReceiver,intentFilter);

				Intent intent = new Intent("cjl.broadcast.test");
				Log.d("cjl", "BroadCastActivity ---------onClick:      ");

				intent.putExtra("msg","local msg");
				localBM.sendBroadcast(intent);

			}
		});


		findViewById(R.id.btnLocalSync).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				IntentFilter intentFilter = new IntentFilter();
				LocalBroadcastManager localBM = LocalBroadcastManager.getInstance(BroadCastActivity.this);
				intentFilter.addAction("cjl.broadcast.test");

				localBM.registerReceiver(localReceiver,intentFilter);
				localBM.registerReceiver(localReceiver2,intentFilter);

//				Intent intent = new Intent("cjl.broadcast.test");
//				Log.d("cjl", "BroadCastActivity ---------onClick:      ");
//
//				intent.putExtra("msg","local msg sync");
//				localBM.sendBroadcast(intent);

				HandlerThread thread = new HandlerThread("TestThread");
				thread.start();

				Handler handler = new Handler(thread.getLooper());
				handler.post(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent("cjl.broadcast.test");
						Log.d("cjl", "BroadCastActivity ---------onClick:      ");

						intent.putExtra("msg","local msg sync");
						LocalBroadcastManager.getInstance(BroadCastActivity.this).sendBroadcast(intent);
					}
				});






			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		LocalBroadcastManager.getInstance(this).unregisterReceiver(localReceiver);
		LocalBroadcastManager.getInstance(this).unregisterReceiver(localReceiver2);

	}
}
