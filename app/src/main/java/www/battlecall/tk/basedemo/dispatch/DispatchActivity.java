package www.battlecall.tk.basedemo.dispatch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Method;

import www.battlecall.tk.basedemo.R;

public class DispatchActivity extends AppCompatActivity {
	private static final String TAG = DispatchActivity.class.getSimpleName();
	private Button button,button2;
	private ViewGroup mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
		mLayout = findViewById(R.id.root);

//		mLayout.requestDisallowInterceptTouchEvent(false);

		button = findViewById(R.id.btn);
		button2 = findViewById(R.id.btn2);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick: 1");
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick: 2");
			}
		});
		mLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick: ViewGroup");
			}
		});
    }

//	@Override
//	public void onUserInteraction() {
////		super.onUserInteraction();
//		Log.d(TAG, "onUserInteraction: ");
//		return;
//	}
//

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		super.dispatchTouchEvent(ev);

//		final int slop = ViewConfiguration.get(this).getScaledTouchSlop();
//		Log.d(TAG, "dispatchTouchEvent: slop "+slop);

//		ViewGroup v;
//		v.requestDisallowInterceptTouchEvent(true);

		Log.d(TAG, "dispatchTouchEvent: 1   "+ev.getAction());
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			onUserInteraction();
		}

//		if(getWindow().superDispatchTouchEvent(ev)){
//			Log.d(TAG, "dispatchTouchEvent: 2");
//			return true;
//		}
		Log.d(TAG, "dispatchTouchEvent: 3");

		return onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		super.onTouchEvent(event);

		try {
			Class clazz = Class.forName("android.view.Window");
			Method method = clazz.getMethod("shouldCloseOnTouch",new Class[]{Context.class,MotionEvent.class});
			Object obj = method.invoke(getWindow(),new Object[]{this,event});
			Log.d(TAG, "onTouchEvent:  obj "+obj);
			if((Boolean) obj){
				finish();
				return true;
			}
		}catch (Exception e){
			return false;
		}
		return false;


//		if (getWindow().shouldCloseOnTouch(this, event)) {
//			finish();
//			return true;
//		}
//
//		return false;
	}
}
