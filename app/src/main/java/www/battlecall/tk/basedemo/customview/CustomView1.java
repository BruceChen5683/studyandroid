package www.battlecall.tk.basedemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BattleCall on 2018/3/27.
 */

public class CustomView1 extends ViewGroup{

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	public CustomView1(Context context) {
		super(context);
	}

	public CustomView1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public CustomView1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i <getChildCount();i++){
			View child = getChildAt(i);

			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();

			int mLeft = (r - width)/2;
			int mTop = (b -height)/2;
			int mRight = mLeft + width;
			int mBottom = mTop + height;
			child.layout(mLeft,mTop,mRight,mBottom);
		}
	}
}
