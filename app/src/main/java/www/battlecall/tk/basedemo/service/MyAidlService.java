package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import www.battlecall.tk.basedemo.IMyAidl;

public class MyAidlService extends Service {

	private ArrayList<Person> persons;

	private IBinder binder = new IMyAidl.Stub(){

		@Override
		public void addPerson(Person person) throws RemoteException {
			persons.add(person);
			Log.d("cjl", "MyAidlService ---------addPerson:      android.os.Process.myPid() "+android.os.Process.myPid());
		}

		@Override
		public List<Person> getPersonList() throws RemoteException {
			return persons;
		}
	};


	public MyAidlService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("cjl", "MyAidlService ---------onBind:      ");
		persons = new ArrayList<>();
		return binder;
	}
}
