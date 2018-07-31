package www.battlecall.tk.basedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import www.battlecall.tk.basedemo.R;

public class StartupModeActivitySingTask extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup_mode_sing_task);

		findViewById(R.id.startStand).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(StartupModeActivitySingTask.this,StartupModeActivityStandard.class);
				startActivity(intent);
			}
		});
	}
}
