package www.battlecall.tk.basedemo.keepalive;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by BattleCall on 2018/4/16.
 */

public class PushService extends Service{

	private final static long WAKE_INTERVAL = 2 * 60 * 1000;


//	private Context context;
//	public PushService(Context context){
//		this.context = context;
//	}
//	private Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			Toast.makeText(context,"----------- "+System.currentTimeMillis(),Toast.LENGTH_SHORT).show();
//		}
//	};


	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("PushService", "onCreate: ");
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("PushService", "onStartCommand: ");
//		Toast.makeText(this,"Service started",Toast.LENGTH_SHORT).show();
////利用notification提升进程权限
//		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){
//			startForeground(1001,new Notification());
//		}else {
//			startForeground(1001,new Notification());
//			Intent sendIntend = new Intent(this,SlaveService.class);
//			startService(sendIntend);
//		}



		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
			jobScheduler.cancelAll();
			JobInfo.Builder builder = new JobInfo.Builder(1024,new ComponentName(getPackageName(),ScheduleService.class.getName()));
			builder.setPeriodic(WAKE_INTERVAL);
			builder.setPersisted(true);
			builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
			 int schedule = jobScheduler.schedule(builder.build());
			if(schedule <= 0){
				Log.e("", "onStartCommand: schedule error");
			}
		}else {
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			Intent alarmIntent = new Intent(getApplication(),PushService.class);
			PendingIntent pI = PendingIntent.getService(getApplicationContext(),1024,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
			am.cancel(pI);
			am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+WAKE_INTERVAL,WAKE_INTERVAL,pI);
		}


		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <100;i++){
					Log.d("PushService", "run: -----"+Utils.getProcessOomAdj(Process.myPid()));
					Log.d("PushService", "run: "+Thread.currentThread().getName());
//					handler.sendEmptyMessage(0);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i == 10){
						Log.d("", "run: stop");
						onDestroy();
					}
				}
			}
		}).start();

		return START_STICKY;
//		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

//		Intent intent = new Intent(getApplicationContext(),PushService.class);
//		startService(intent);
	}
}
