package www.battlecall.tk.basedemo.keepalive;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by BattleCall on 2018/4/16.
 */

public class SlaveService extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(1001,new Notification());
		stopForeground(true);
		stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}
}
