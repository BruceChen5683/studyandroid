package www.battlecall.tk.basedemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by BattleCall on 2018/8/1.
 */

public class LocalReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("cjl", "LocalReceiver ---------onReceive:      "+intent.getStringExtra("msg"));

		Toast.makeText(context,"My thread is "+Thread.currentThread().getName(),Toast.LENGTH_LONG).show();
	}
}
