package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import www.battlecall.tk.basedemo.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

	private Button btnStartservice,btnStopservice,btnBindservice,btnUnBindservice,btnCount,btnsayHi,btnstartForeford,btnstopForeford;

	private ServiceConnection conn;
//	private MyService mService;
	private Messenger mService;
	private boolean mFlag;

	Messenger receiverMessenger = new Messenger(new ReceiverMsgHander());
	private static class ReceiverMsgHander extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case MessengerService.MSG_SAY_HI:
					Log.d("cjl", "ReceiverMsgHander ---------handleMessage:      "+msg.getData().get("reply"));
					break;
				default:
					break;
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("cjl", "ServiceActivity ---------onCreate:      android.os.Process.myPid() "+android.os.Process.myPid());
		setContentView(R.layout.activity_service);

		btnStartservice = findViewById(R.id.startService);
		btnStopservice = findViewById(R.id.stopService);
		btnBindservice = findViewById(R.id.bindSerice);
		btnUnBindservice = findViewById(R.id.unbindservice);
		btnCount = findViewById(R.id.getCount);
		btnsayHi = findViewById(R.id.sayHi);
		btnstartForeford = findViewById(R.id.startForeford);
		btnstopForeford = findViewById(R.id.stopForeford);

		btnStartservice.setOnClickListener(this);
		btnStopservice.setOnClickListener(this);
		btnBindservice.setOnClickListener(this);
		btnUnBindservice.setOnClickListener(this);
		btnCount.setOnClickListener(this);
		btnsayHi.setOnClickListener(this);
		btnstartForeford.setOnClickListener(this);
		btnstopForeford.setOnClickListener(this);

		conn = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				/**
				 * 通过服务端传递的IBinder对象,创建相应的Messenger
				 * 通过该Messenger对象与服务端进行交互
				 */
				mService = new Messenger(service);
				mFlag = true;
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				mService = null;
				mFlag = false;
			}
		};
//
//		conn = new ServiceConnection() {
//			/**
//			 * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
//			 * 通过这个IBinder对象，实现宿主和Service的交互。
//			 */
//			@Override
//			public void onServiceConnected(ComponentName name, IBinder service) {
//				Log.d("cjl", "ServiceActivity ---------onServiceConnected:      ");
//				MyService.LocalBinder binder = (MyService.LocalBinder) service;
//				mService = binder.getService();
//			}
//
//			/**
//			 * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
//			 * 例如内存的资源不足时这个方法才被自动调用。
//			 *
//			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
//			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
//			 * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
//			 */
//			@Override
//			public void onServiceDisconnected(ComponentName name) {
//				Log.d("cjl", "ServiceActivity ---------onServiceDisconnected:      ");
//				mService = null;
//			}
//		};
//
	}

	private void sayHi(View v){
		if(!mFlag){
			return;
		}

		Message msg = Message.obtain(null,MessengerService.MSG_SAY_HI,0,0);
		msg.replyTo = receiverMessenger;
		try {
			mService.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
				Log.d("cjl", "ServiceActivity ---------onClick:      bind");
//				intent.setClass(ServiceActivity.this,MyService.class);
				intent.setClass(ServiceActivity.this,MessengerService.class);
//				TODO  not BIND_AUTO_CREATE,0x0000
				bindService(intent,conn, Service.BIND_AUTO_CREATE);
				break;
			case R.id.unbindservice:
//				if(mService != null){
//					mService = null;
//					unbindService(conn);
//				}

				if (mFlag){
					Log.d("cjl", "ServiceActivity ---------onClick:      unbind");
					unbindService(conn);
					mFlag = false;
				}
				break;

			case R.id.getCount:
//				if (mService != null){
//					Log.d("cjl", "ServiceActivity ---------onClick:      "+mService.getCount());
//				}else {
//					Log.d("cjl", "ServiceActivity ---------onClick:      Service null");
//				}
				break;

			case R.id.sayHi:
				Log.d("cjl", "ServiceActivity ---------onClick:      android.os.Process.myPid() "+android.os.Process.myPid());
				sayHi(v);
				break;

			case R.id.startForeford:
//				intent.setClass(ServiceActivity.this,ForegroundService.class);
//				intent.setPackage(getPackageName());
				intent.setAction("cjl.myForegroundService");
				final Intent serviceIntent = new Intent(getExplicitIntent(ServiceActivity.this,intent));
				serviceIntent.putExtra("cmd",0);
				startService(serviceIntent);
				break;
			case R.id.stopForeford:
				Log.d("cjl", "ServiceActivity ---------onClick:   stopForeford   ");

//				intent.setClass(ServiceActivity.this,ForegroundService.class);
//				intent.putExtra("cmd",1);//0 start 1 close

				intent.setAction("cjl.myForegroundService");
				final Intent serviceIntent1 = new Intent(getExplicitIntent(ServiceActivity.this,intent));
				serviceIntent1.putExtra("cmd",1);
				startService(serviceIntent1);
//				startService(intent);
				break;
			default:
				break;
		}
	}

	public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
		Log.d("cjl", "ServiceActivity ---------getExplicitIntent:      ");
		// Retrieve all services that can match the given intent
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
		// Make sure only one match was found
		if (resolveInfo == null || resolveInfo.size() != 1) {
			Log.d("cjl", "ServiceActivity ---------getExplicitIntent:      null");
			return null;
		}
		// Get component info and create ComponentName
		ResolveInfo serviceInfo = resolveInfo.get(0);
		String packageName = serviceInfo.serviceInfo.packageName;
		String className = serviceInfo.serviceInfo.name;
		ComponentName component = new ComponentName(packageName, className);
		// Create a new intent. Use the old one for extras and such reuse
		Intent explicitIntent = new Intent(implicitIntent);
		// Set the component to be explicit
		explicitIntent.setComponent(component);
		return explicitIntent;
	}
}
