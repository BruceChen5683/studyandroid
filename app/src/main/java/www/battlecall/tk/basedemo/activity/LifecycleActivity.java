package www.battlecall.tk.basedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import www.battlecall.tk.basedemo.R;

public class LifecycleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycle);
		Log.d("cjl", "LifecycleActivity ---------onCreate:      ");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("cjl", "LifecycleActivity ---------onStart:      ");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("cjl", "LifecycleActivity ---------onResume:      ");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("cjl", "LifecycleActivity ---------onRestart:      ");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("cjl", "LifecycleActivity ---------onPause:      ");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("cjl", "LifecycleActivity ---------onStop:      ");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("cjl", "LifecycleActivity ---------onDestroy:      ");
	}
}
