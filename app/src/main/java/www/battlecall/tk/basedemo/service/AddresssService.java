package www.battlecall.tk.basedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import www.battlecall.tk.basedemo.AddressAidl;

/**
 * Created by BattleCall on 2018/8/9.
 */

public class AddresssService extends Service{

	private List<Address> addresses = new ArrayList<>();

	private final AddressAidl.Stub addressManager = new AddressAidl.Stub(){

		@Override
		public Address addAddressIn(Address person) throws RemoteException {
			return person;
		}

		@Override
		public Address addAddressOut(Address person) throws RemoteException {
			return person;
		}

		@Override
		public Address addAddressInout(Address person) throws RemoteException {
			return person;
		}
	};

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
