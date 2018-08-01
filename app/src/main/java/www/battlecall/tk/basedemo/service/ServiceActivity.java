package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

	private Button btnStartservice,btnStopservice,btnBindservice,btnUnBindservice,btnCount;

	private ServiceConnection conn;
	private MyService mService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		btnStartservice = findViewById(R.id.startService);
		btnStopservice = findViewById(R.id.stopService);
		btnBindservice = findViewById(R.id.bindSerice);
		btnUnBindservice = findViewById(R.id.unbindservice);
		btnCount = findViewById(R.id.getCount);

		btnStartservice.setOnClickListener(this);
		btnStopservice.setOnClickListener(this);
		btnBindservice.setOnClickListener(this);
		btnUnBindservice.setOnClickListener(this);
		btnCount.setOnClickListener(this);

		conn = new ServiceConnection() {
			/**
			 * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
			 * 通过这个IBinder对象，实现宿主和Service的交互。
			 */
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d("cjl", "ServiceActivity ---------onServiceConnected:      ");
				MyService.LocalBinder binder = (MyService.LocalBinder) service;
				mService = binder.getService();
			}

			/**
			 * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
			 * 例如内存的资源不足时这个方法才被自动调用。
			 *
			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
			 */
			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.d("cjl", "ServiceActivity ---------onServiceDisconnected:      ");
				mService = null;
			}
		};

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();

		switch (v.getId()){
			case R.id.startService:
				intent.setClass(ServiceActivity.this,MyService.class);
				startService(intent);
				break;
			case R.id.stopService:
				intent.setClass(ServiceActivity.this,MyService.class);
				stopService(intent);
				break;

			case R.id.bindSerice:
				intent.setClass(ServiceActivity.this,MyService.class);
//TODO  not BIND_AUTO_CREATE,0x0000
				bindService(intent,conn, Service.BIND_AUTO_CREATE);
				break;
			case R.id.unbindservice:
				if(mService != null){
					mService = null;
					unbindService(conn);
				}
				break;
			
			case R.id.getCount:
				if (mService != null){
					Log.d("cjl", "ServiceActivity ---------onClick:      "+mService.getCount());
				}else {
					Log.d("cjl", "ServiceActivity ---------onClick:      Service null");
				}
				break;

			default:
				break;
		}
	}
}
