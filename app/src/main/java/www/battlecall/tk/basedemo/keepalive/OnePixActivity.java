package www.battlecall.tk.basedemo.keepalive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

import www.battlecall.tk.basedemo.R;

public class OnePixActivity extends AppCompatActivity {

	public static WeakReference<OnePixActivity> instance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("run", "onCreate: ");
		Window window = getWindow();
		window.setGravity(Gravity.TOP | Gravity.LEFT);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.x=0;
		lp.y=0;
		lp.height=1;
		lp.width=1;
		window.setAttributes(lp);
		setContentView(R.layout.activity_one_pix);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (1==1){
					Log.d("", "run: -----------");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(DeviceUtils.isScreenOn(this)){
			finishSelf();//TODO 和finish对比下？
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		finishSelf();
		return super.dispatchTouchEvent(ev);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (instance != null && instance.get() == this){
			instance = null;
		}
	}

	public void finishSelf(){
		if(!isFinishing()){
			finish();
		}
	}
}
