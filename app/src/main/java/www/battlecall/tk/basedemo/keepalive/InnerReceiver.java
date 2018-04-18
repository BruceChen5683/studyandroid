package www.battlecall.tk.basedemo.keepalive;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by BattleCall on 2018/4/11.
 */

public class InnerReceiver extends BroadcastReceiver{
	final String SYSTEM_DIALOG_REASON_KEY = "reason";

	final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

	final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

	private Context context;
	public InnerReceiver(Context context){
		this.context = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.d("", "onReceive:  action "+action);
		if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
			String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
			Log.d("", "onReceive:  reason "+reason);

			if (reason != null) {
				if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
					Toast.makeText(context, "Home键被监听", Toast.LENGTH_SHORT).show();
//kill or increase
////					Log.d("", "onReceive: -----"+Utils.getProcessOomAdj(Process.myPid()));//获取当前进程oom_adj
				} else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
					Toast.makeText(context, "多任务键被监听", Toast.LENGTH_SHORT).show();
				}
			}
		}

		if (Intent.ACTION_SCREEN_OFF.equals(action)){
			Log.d("", "onReceive: start OnePix");
			Intent intent2 = new Intent(context,OnePixActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}else if(Intent.ACTION_USER_PRESENT.equals(action) || Intent.ACTION_SCREEN_ON.equals(action)){
			Log.d("", "onReceive: close OnePix");
			OnePixActivity onePixActivity = OnePixActivity.instance != null ? OnePixActivity.instance.get():null;
			if(onePixActivity != null){
				onePixActivity.finishSelf();
			}
		}
	}

	//进程杀死
	private void myKill(){

//		//1.
//		final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//		Class clazz = null;
//		try {
//			clazz = Class.forName("android.app.ActivityManager");
//			Method method = clazz.getMethod("forceStopPackage", new Class[]{String.class});
//			method.setAccessible(true);
//			method.invoke(activityManager,context.getPackageName());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}


		//2.
		final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		activityManager.killBackgroundProcesses(context.getPackageName());
//
//		//3.
//		Process.killProcess(Process.myPid());
	}

	private void increasePritorityByNotification(){
		context.startService(new Intent(((Activity)context).getBaseContext(),PushService.class));
	}
}



//测试 oom_adj 0   ???TODO 降低oom_adj   或者其他kill方法，killBackgroundProcesses