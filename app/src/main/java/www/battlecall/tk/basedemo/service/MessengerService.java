package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MessengerService extends Service {
	private static final int MSG_SAY_HI = 1;
	public MessengerService() {
	}

	class IncomingHander extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case MSG_SAY_HI:
					Log.d("cjl", "IncomingHander ---------handleMessage:     hi ");
					break;
				default:
					super.handleMessage(msg);
			}

		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return messenger.getBinder();
	}

	final Messenger messenger = new Messenger(new IncomingHander());
}
