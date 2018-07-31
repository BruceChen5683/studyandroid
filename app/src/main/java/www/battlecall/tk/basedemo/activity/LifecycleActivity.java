package www.battlecall.tk.basedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.battlecall.tk.basedemo.R;

public class LifecycleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycle);
	}
}
