package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {

	private final int DELAY = 2000;

	private LocalBinder binder = new LocalBinder();
	private Thread thread;
	private boolean quit;
	private int count;

	public class LocalBinder extends Binder{
		MyService getService(){
			return MyService.this;
		}
	}
//	private Handler mHandler = new Handler();
//	private Runnable mTask =  new Runnable(){
//		@Override
//		public void run() {
//			Log.d("cjl", "MyService ---------run:      "+DELAY/1000+"  s after");
//			Integer.parseInt("ok");
//		}
//	};
	public MyService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service
		Log.d("cjl", "MyService ---------onBind:      ");
		return binder;
//		throw new UnsupportedOperationException("Not yet implemented");
	}


	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("cjl", "MyService ---------onCreate:      ");
//		mHandler.postDelayed(mTask,DELAY);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!quit){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
				}
			}
		}).start();
	}
	
	public int getCount(){
		return count;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d("cjl", "MyService ---------onUnbind:      ");
		return super.onUnbind(intent);
	}

	//	START_STICKY
// 08-01 11:52:57.425 28062-28062/www.battlecall.tk.basedemo D/cjl: MyService ---------onCreate:
//			08-01 11:52:57.425 28062-28062/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      startId  1
//			08-01 11:52:57.425 28062-28062/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      intent Intent { cmp=www.battlecall.tk.basedemo/.service.MyService }
//08-01 11:52:59.428 28062-28062/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after
	//Crash
//08-01 11:53:20.187 30014-30014/? D/cjl: MyService ---------onCreate:
//			08-01 11:53:20.187 30014-30014/? D/cjl: MyService ---------onStartCommand:      startId  2
//			08-01 11:53:20.187 30014-30014/? D/cjl: MyService ---------onStartCommand:      intent null
//			08-01 11:53:22.189 30014-30014/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after

//	START_STICKY_COMPATIBILITY
//	08-01 11:54:52.756 30294-30294/www.battlecall.tk.basedemo D/cjl: MyService ---------onCreate:
//			08-01 11:54:52.756 30294-30294/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      startId  1
//			08-01 11:54:52.756 30294-30294/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      intent Intent { cmp=www.battlecall.tk.basedemo/.service.MyService }
//08-01 11:54:54.759 30294-30294/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after
	//Crash
//08-01 11:54:57.184 30559-30559/www.battlecall.tk.basedemo D/cjl: MyService ---------onCreate:
//			08-01 11:54:59.188 30559-30559/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after

//	START_NOT_STICKY
//	08-01 11:55:59.239 30770-30770/www.battlecall.tk.basedemo D/cjl: MyService ---------onCreate:
//			08-01 11:55:59.239 30770-30770/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      startId  1
//			08-01 11:55:59.239 30770-30770/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      intent Intent { cmp=www.battlecall.tk.basedemo/.service.MyService }
//08-01 11:56:01.242 30770-30770/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after
	//Crash
	//未重新创建

//	START_REDELIVER_INTENT
//	08-01 11:57:29.400 319-319/www.battlecall.tk.basedemo D/cjl: MyService ---------onCreate:
//			08-01 11:57:29.401 319-319/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      startId  1
//			08-01 11:57:29.401 319-319/www.battlecall.tk.basedemo D/cjl: MyService ---------onStartCommand:      intent Intent { cmp=www.battlecall.tk.basedemo/.service.MyService }
//08-01 11:57:31.403 319-319/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after
	//Crash   startid  & intent
//08-01 11:57:33.838 738-738/? D/cjl: MyService ---------onCreate:
//			08-01 11:57:33.838 738-738/? D/cjl: MyService ---------onStartCommand:      startId  1
//			08-01 11:57:33.839 738-738/? D/cjl: MyService ---------onStartCommand:      intent Intent { cmp=www.battlecall.tk.basedemo/.service.MyService }
//08-01 11:57:35.839 738-738/www.battlecall.tk.basedemo D/cjl: MyService ---------run:      2  s after

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("cjl", "MyService ---------onStartCommand:      startId  "+ startId);
		Log.d("cjl", "MyService ---------onStartCommand:      intent "+intent);
		return START_STICKY;
//		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		this.quit = true;
		super.onDestroy();
		Log.d("cjl", "MyService ---------onDestroy:      ");
	}
}
