package www.battlecall.tk.basedemo.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import www.battlecall.tk.basedemo.R;

public class ViewActivity extends AppCompatActivity {
	private final static String TAG = ViewActivity.class.getSimpleName();
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);

		btn = findViewById(R.id.customview_btn);
		btn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "onTouchEvent: x "+event.getX());
				Log.d(TAG, "onTouchEvent: y"+event.getY());
				Log.d(TAG, "onTouchEvent: rawx"+event.getRawX());
				Log.d(TAG, "onTouchEvent: rawy"+event.getRawY());
				return false;
			}
		});
		Log.d(TAG, "onCreate: "+ ViewActivity.class);
		Log.d(TAG, "onCreate: "+this.getClass());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent: x "+event.getX());
		Log.d(TAG, "onTouchEvent: y"+event.getY());
		Log.d(TAG, "onTouchEvent: rawx"+event.getRawX());
		Log.d(TAG, "onTouchEvent: rawy"+event.getRawY());


		return super.onTouchEvent(event);
	}
}
