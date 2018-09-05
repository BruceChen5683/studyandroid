package www.battlecall.tk.basedemo.dispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import www.battlecall.tk.basedemo.R;

public class DispatchActivity2 extends AppCompatActivity {

	MyLayout mLayout;
	Button button,button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispatch2);

		mLayout = findViewById(R.id.mylayout);

		button = findViewById(R.id.btn1);
		button2 = findViewById(R.id.btn2);

		mLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d("cjl", "DispatchActivity2 -------mLayout--onTouch:      ");
				return false;
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("cjl", "DispatchActivity2 -----button2----onClick:      ");
			}
		});

//		Log.d("cjl", "DispatchActivity2 ---------onCreate:  utton.isClickable()   "+ button.isClickable());

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("cjl", "DispatchActivity2 -----button----onClick:      "+((Button)v).isClickable());
			}
		});

	}

}
