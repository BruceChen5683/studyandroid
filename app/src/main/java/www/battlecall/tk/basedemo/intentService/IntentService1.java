package www.battlecall.tk.basedemo.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by BattleCall on 2018/8/27.
 */

public class IntentService1 extends IntentService{

	public IntentService1(){
		super("mydefaultItentService");
	}
	public IntentService1(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		boolean test = intent.getBooleanExtra("test",true);
		if (test){
			Log.d("cjl", "IntentService1 ---------onHandleIntent:      TRUE");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			Log.d("cjl", "IntentService1 ---------onHandleIntent:      FALSE");
		}
	}


	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("cjl", "IntentService1 ---------onCreate:      ");
	}

	@Override
	public void onStart(@Nullable Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d("cjl", "IntentService1 ---------onStart:      ");
	}
}
