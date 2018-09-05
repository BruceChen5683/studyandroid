package www.battlecall.tk.basedemo.dispatch;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by BattleCall on 2018/9/5.
 */

public class MyLayout extends LinearLayout{
	public MyLayout(Context context) {
		super(context);
	}

	public MyLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public MyLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d("cjl", "MyLayout ---------onInterceptTouchEvent:      ");
		return true;//super.onInterceptTouchEvent(ev);
	}
}
