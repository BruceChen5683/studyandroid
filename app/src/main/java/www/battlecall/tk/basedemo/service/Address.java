package www.battlecall.tk.basedemo.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BattleCall on 2018/8/9.
 */

public class Address implements Parcelable{
	private  String street;

	public Address(String street) {
		this.street = street;
	}


	protected Address(Parcel in) {
		street = in.readString();
	}

	public static final Creator<Address> CREATOR = new Creator<Address>() {
		@Override
		public Address createFromParcel(Parcel in) {
			return new Address(in);
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(street);
	}

	@Override
	public String toString() {
		return "Address{" +
				"street='" + street + '\'' +
				'}';
	}
}
