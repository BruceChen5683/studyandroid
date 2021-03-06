package www.battlecall.tk.basedemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import www.battlecall.tk.basedemo.R;

public class ForegroundService extends Service {

	/**
	 * id不可设置为0,否则不能设置为前台service
	 */
	private static final int NOTIFICATION_DOWNLOAD_PROGRESS_ID = 0x0001;

	private boolean isRemove=false;//是否需要移除

	public ForegroundService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public void createNotification(){
		Log.d("cjl", "ForegroundService ---------createNotification:      ");
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
		builder.setAutoCancel(false);
		builder.setOngoing(true);
		builder.setShowWhen(true);
		builder.setContentTitle("foreground service");
		Notification notification = builder.build();
		startForeground(NOTIFICATION_DOWNLOAD_PROGRESS_ID,notification);
		Log.d("cjl", "ForegroundService ---------createNotification:    notification  "+notification.visibility);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int i = intent.getExtras().getInt("cmd");
		Log.d("cjl", "ForegroundService ---------onStartCommand:      i "+i);
		if (i == 0){
			Log.d("cjl", "ForegroundService ---------onStartCommand:      1");
			if(!isRemove){
				Log.d("cjl", "ForegroundService ---------onStartCommand:      2");
				createNotification();
			}
			isRemove = true;
		}else {
			if(isRemove){
				stopForeground(true);
			}
			isRemove = false;
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if(isRemove){
			stopForeground(true);
		}
		isRemove = false;
		super.onDestroy();
	}
	/**
	 *如何保证服务不被杀死
	 *onStartCommand  返回 START_STICKY或START_REDELIVER_INTENT
	 *
	 * ondestroy A，Bservice互相启动
	 *
	 *
	 * forceStop不可强制结束？
	 *
	 * */
}
