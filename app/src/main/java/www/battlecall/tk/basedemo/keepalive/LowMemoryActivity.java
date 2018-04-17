package www.battlecall.tk.basedemo.keepalive;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.battlecall.tk.basedemo.R;

public class LowMemoryActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_low_memory);
	}


	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
}
