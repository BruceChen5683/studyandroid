package www.battlecall.tk.basedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import www.battlecall.tk.basedemo.R;

public class StartupModeActivitySingleTop extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup_mode_single_top);

		findViewById(R.id.startSelf).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("cjl", "StartupModeActivitySingleTop ---------onClick:      ");
				Intent intent = new Intent();
				intent.setClass(StartupModeActivitySingleTop.this,StartupModeActivitySingleTop.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.startStand).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("cjl", "StartupModeActivitySingleTop ---------onClick:      ");
				Intent intent = new Intent();
				intent.setClass(StartupModeActivitySingleTop.this,StartupModeActivityStandard.class);
				startActivity(intent);
			}
		});
	}
}
