package www.battlecall.tk.basedemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import www.battlecall.tk.basedemo.R;

/**
 * Created by BattleCall on 2018/9/1.
 */

public class CircleView extends View{
	private Paint mPaint;
	private int color;
	private float dp;
	public CircleView(Context context) {
		super(context);
		Log.d("cjl", "CircleView ---------CircleView:     1 ");
		init();
	}

	public CircleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs,0);
		Log.d("cjl", "CircleView ---------CircleView:    2  ");

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
		color = a.getColor(R.styleable.CircleView_circle_color,Color.BLACK);
		dp = a.getDimension(R.styleable.CircleView_circle_top,10);

		Log.d("cjl", "CircleView ---------CircleView:      color "+color);
		Log.d("cjl", "CircleView ---------CircleView:      dp "+dp);

		a.recycle();

		init();
	}

	public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		Log.d("cjl", "CircleView ---------CircleView:     3 ");
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
		color = a.getColor(R.styleable.CircleView_circle_color,Color.BLACK);
		dp = a.getDimension(R.styleable.CircleView_circle_top,10);

		Log.d("cjl", "CircleView ---------CircleView:      "+color);
		a.recycle();

		init();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init(){
		mPaint = new Paint();
		mPaint.setColor(color);
		mPaint.setStrokeWidth(5f);
		mPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//增加对wrap_content的支持
		Log.d("cjl", "CircleView ---------onMeasure:    widthMeasureSpec   "+widthMeasureSpec);
		Log.d("cjl", "CircleView ---------onMeasure:    heightMeasureSpec   "+heightMeasureSpec);

		Log.d("cjl", "CircleView ---------onMeasure:    getSuggestedMinimumHeight  "+getSuggestedMinimumHeight());
		Log.d("cjl", "CircleView ---------onMeasure:    getSuggestedMinimumWidth   "+getSuggestedMinimumWidth());

		Log.d("cjl", "CircleView ---------onMeasure:      MeasureSpec.getSize(measureSpec) "+MeasureSpec.getSize(widthMeasureSpec));
		Log.d("cjl", "CircleView ---------onMeasure:      MeasureSpec.getSize(measureSpec) "+MeasureSpec.getSize(heightMeasureSpec));

		// 获取宽-测量规则的模式和大小
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		// 获取高-测量规则的模式和大小
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		// 设置wrap_content的默认宽 / 高值
		// 默认宽/高的设定并无固定依据,根据需要灵活设置
		// 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
		int mWidth = 400;
		int mHeight = 400;

		// 当布局参数设置为wrap_content时，设置默认值
		if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
			setMeasuredDimension(mWidth, mHeight);
			// 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
		} else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
			setMeasuredDimension(mWidth, heightSize);
		} else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
			setMeasuredDimension(widthSize, mHeight);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

//		int width = getWidth();
//		int height = getHeight();
//		Log.d("cjl", "CircleView ---------onDraw:      width "+width);
//		Log.d("cjl", "CircleView ---------onDraw:      height "+height);
//		int r = Math.min(width,height)/2;
//
//		canvas.drawCircle(width/2,height/2,r,mPaint);


//		//support padding
		// 获取传入的padding值
		final int paddingLeft = getPaddingLeft();
		final int paddingRight = getPaddingRight();
		final int paddingTop = getPaddingTop();
		final int paddingBottom = getPaddingBottom();


		// 获取绘制内容的高度和宽度（考虑了四个方向的padding值）
		int width = getWidth() - paddingLeft - paddingRight ;
		int height = getHeight() - paddingTop - paddingBottom ;

		// 设置圆的半径 = 宽,高最小值的2分之1
		int r = Math.min(width, height)/2;

		// 画出圆(蓝色)
		// 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
//		canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,r,mPaint);
		canvas.drawCircle(paddingLeft+width/2,dp+height/2,r,mPaint);

	}

}
