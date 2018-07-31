package www.battlecall.tk.basedemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class StartupModeActivityA extends AppCompatActivity implements View.OnClickListener{

	Button btnStandard,btnSingleTop,btnSingleTask,btnSingleInstance,btnScheme;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup_mode_activity);

		btnStandard = findViewById(R.id.standard);
		btnSingleTop = findViewById(R.id.singletop);
		btnSingleTask = findViewById(R.id.singleTask);
		btnSingleInstance = findViewById(R.id.singleInstance);
		btnScheme = findViewById(R.id.scheme);
		btnStandard.setOnClickListener(this);
		btnSingleTop.setOnClickListener(this);
		btnSingleTask.setOnClickListener(this);
		btnSingleInstance.setOnClickListener(this);
		btnScheme.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mycheme://cjl/test")));
			}
		});

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()){
			case R.id.standard:
				intent.setClass(StartupModeActivityA.this,StartupModeActivityStandard.class);
				break;
			case R.id.singletop:
				intent.setClass(StartupModeActivityA.this,StartupModeActivitySingleTop.class);
				break;
			case R.id.singleTask:
				intent.setClass(StartupModeActivityA.this,StartupModeActivitySingTask.class);
				break;
			case R.id.singleInstance:
				intent.setClass(StartupModeActivityA.this,StartupModeActivitySingleInstance.class);
				break;
			default:
				Log.e("cjl", "StartupModeActivityA ---------onClick:      Error");
				break;
		}

		startActivity(intent);
	}
}
