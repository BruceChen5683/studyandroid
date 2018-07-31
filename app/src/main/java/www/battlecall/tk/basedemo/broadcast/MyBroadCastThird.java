package www.battlecall.tk.basedemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by BattleCall on 2018/6/21.
 */

public class MyBroadCastThird extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d("cjl", "MyBroadCastThird ---------onReceive:      " +getResultData());

		Log.d("cjl", "MyBroadCastThird ---------onReceive:      "+intent.getStringExtra("msg"));

		Log.d("cjl", "MyBroadCastThird ---------onReceive:      "+getResultExtras(true).get("msg"));

		Bundle bundle = new Bundle();
		bundle.putString("msg","from myc one");
		setResultExtras(bundle);
	}
}
