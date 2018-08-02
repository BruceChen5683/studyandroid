package www.battlecall.tk.basedemo.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

import www.battlecall.tk.basedemo.IMyAidl;
import www.battlecall.tk.basedemo.R;

public class ServiceAIDLActivity extends AppCompatActivity {


	private IMyAidl myAidl;

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myAidl = IMyAidl.Stub.asInterface(service);
//			myAidl = (IMyAidl) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			myAidl = null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("cjl", "ServiceAIDLActivity ---------onCreate:    android.os.Process.myPid()  "+android.os.Process.myPid());
		setContentView(R.layout.activity_service_aidl);

		findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),MyAidlService.class);
				bindService(intent,connection,BIND_AUTO_CREATE);
			}
		});

		findViewById(R.id.addPerson).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Random random = new Random();
				try {
					myAidl.addPerson(new Person("cjl" + random.nextInt(10)));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		findViewById(R.id.getPerson).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					for (Person person :myAidl.getPersonList()){
						Log.d("cjl", "ServiceAIDLActivity ---------onClick:      "+person);

					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});


	}
}
