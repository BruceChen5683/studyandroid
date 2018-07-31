
package www.battlecall.tk.basedemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by BattleCall on 2018/6/21.
 */

public class MyBroadCastTwo extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("cjl", "MyBroadCastTwo ---------onReceive:      "+intent.getStringExtra("msg"));

		Bundle bundle = new Bundle();
		bundle.putString("msg","from myc two");
		setResultExtras(bundle);

		setResultData("result data form two");
	}
}
