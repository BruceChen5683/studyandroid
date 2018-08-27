package www.battlecall.tk.basedemo.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.battlecall.tk.basedemo.R;

public class IntentServiceActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_service);


		Intent intent = new Intent();
		intent.setClass(this,IntentService1.class);
		intent.putExtra("test",true);
		startService(intent);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		intent.putExtra("test",false);
		startService(intent);

	}

}
