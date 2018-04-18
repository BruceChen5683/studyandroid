package www.battlecall.tk.basedemo.keepalive;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

/**
 * Created by BattleCall on 2018/4/17.
 */

public class DeviceUtils {
	public static boolean isScreenOn(Context context){
		PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH){
			return powerManager.isInteractive();
		}else {
			return powerManager.isScreenOn();
		}
	}
}
