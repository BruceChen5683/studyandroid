package www.battlecall.tk.basedemo.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.battlecall.tk.basedemo.R;

public class FragmentMainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_main);


		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		//将FragmentA从容器中移除掉，减少内存的消耗
		//fragmentTransaction.remove(fragmentA);
		fragmentTransaction.add(R.id.dynamic_fragment,new MyFragment());
		fragmentTransaction.commit();
	}
}
