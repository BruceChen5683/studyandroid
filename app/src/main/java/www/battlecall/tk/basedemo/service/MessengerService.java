package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
	public static final int MSG_SAY_HI = 1;
	public MessengerService() {
	}

	class IncomingHander extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case MSG_SAY_HI:
					int pid = android.os.Process.myPid();//获取进程pid

					Messenger client = msg.replyTo;
					Message message = Message.obtain(null,MSG_SAY_HI);
					Bundle bundle = new Bundle();
					bundle.putString("reply","你好客户");
					message.setData(bundle);
					Log.d("cjl", "IncomingHander ---------handleMessage:     hi "+pid);

					try {
						client.send(message);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					break;
				default:
					super.handleMessage(msg);
			}

		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("cjl", "MessengerService ---------onBind:      "+android.os.Process.myPid());
		return messenger.getBinder();
	}

	final Messenger messenger = new Messenger(new IncomingHander());
}
