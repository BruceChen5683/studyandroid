package www.battlecall.tk.basedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import www.battlecall.tk.basedemo.R;

public class StartupModeActivityStandard extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup_mode_standard);

		findViewById(R.id.startSelf).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartupModeActivityStandard.this,StartupModeActivityStandard.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.startTop).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartupModeActivityStandard.this,StartupModeActivitySingleTop.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.startsingleTask).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartupModeActivityStandard.this,StartupModeActivitySingTask.class);
				startActivity(intent);
			}
		});

	}
}
