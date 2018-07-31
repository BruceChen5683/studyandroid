package www.battlecall.tk.basedemo.broadcast;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class InterceptActivity extends AppCompatActivity {

	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intercept);
		btn = findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("cjl.broadcast.test");
				intent.putExtra("msg","first msg");
				sendOrderedBroadcast(intent,"cjl.permission.test");
			}
		});

	}
}
