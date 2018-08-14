// IMyAidl.aidl
package www.battlecall.tk.basedemo;

// Declare any non-default types here with import statements
import www.battlecall.tk.basedemo.service.Address;

interface AddressAidl {

    Address addAddressIn(in Address person);
    Address addAddressOut(in Address person);
    Address addAddressInout(in Address person);

}
