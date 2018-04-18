package www.battlecall.tk.basedemo.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by BattleCall on 2018/4/18.
 */

public class SystemReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("SystemReceiver", "onReceive: ");
		context.startService(new Intent(context,PushService.class));
	}
}
