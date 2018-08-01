package www.battlecall.tk.basedemo.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

	private Button btnStartservice,btnStopservice,btnBindservice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		btnStartservice = findViewById(R.id.startService);
		btnStopservice = findViewById(R.id.stopService);

		btnStartservice.setOnClickListener(this);
		btnStopservice.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()){
			case R.id.startService:
				intent.setClass(ServiceActivity.this,MyService.class);
				
				startService(intent);
				break;
			case R.id.stopService:
				intent.setClass(ServiceActivity.this,MyService.class);
				stopService(intent);
				break;

			default:
				break;
		}
	}
}
