package www.battlecall.tk.basedemo.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.PictureDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import www.battlecall.tk.basedemo.R;

/**
 * Created by BattleCall on 2018/3/28.
 */

public class CustomView extends View{
	private Paint mPaint,mPaint1,mPaint2;
	private Picture mPicture;
	private Path path = new Path();
	private Context mContext;
	private Bitmap mBitmap;

	public CustomView(Context context) {
		super(context);
		mContext = context;
		initPaint();
	}

	public CustomView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

		initPaint();
	}

	public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;

		initPaint();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		mContext = context;

		initPaint();
	}


	private void initPaint() {
		mPaint = new Paint();
		mPaint.setColor(Color.YELLOW);

		mPaint1 = new Paint();
		mPaint1.setStrokeWidth(5.0f);
		mPaint1.setColor(Color.BLUE);
		mPaint1.setFakeBoldText(true);
		mPaint1.setTextSize(40.0f);

		mPaint2 = new Paint();
		mPaint2.setStyle(Paint.Style.STROKE);
		mPaint2.setColor(Color.BLACK);
		mPaint2.setTextSize(40.0f);

		initPicture();
		mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_3d_rotation_black_24dp);
	}

	public void initPicture(){
		mPicture = new Picture();
		Canvas canvas = mPicture.beginRecording(500, 500);
		// 创建一个画笔
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.FILL);

		// 在Canvas中具体操作
		// 位移
		canvas.translate(250,250);
		// 绘制一个圆
		canvas.drawCircle(0,0,100,paint);

		mPicture.endRecording();
//		mPicture.draw(canvas);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.drawColor(Color.RED);

//		canvas.drawPoint(300,300,mPaint1);
//		canvas.drawPoints(new float[]{
//				1,1,
//				2,2,
//				3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10},mPaint2);

//// 画一条直线
//// 在坐标(100,200)，(700,200)之间绘制一条直线
//		canvas.drawLine(100,200,700,200,mPaint1);
//
//// 绘制一组线
//// 在坐标(400,500)，(500,500)之间绘制直线1
//// 在坐标(400,600)，(500,600)之间绘制直线2
//		canvas.drawLines(new float[]{
//				400,500,500,500,
//				400,600,500,600
//		},mPaint2);
//
//
//// 关于绘制矩形，Canvas提供了三种重载方法
//
//		// 方法1：直接传入两个顶点的坐标
//		// 两个顶点坐标分别是：（100,100），（800,400）
//		canvas.drawRect(100,100,800,400,mPaint);
//
//		// 方法2：将两个顶点坐标封装为RectRectF
//		Rect rect = new Rect(100,100,800,400);
//		canvas.drawRect(rect,mPaint);
//
//		// 方法3：将两个顶点坐标封装为RectF
//		RectF rectF = new RectF(100,100,800,400);
//		canvas.drawRect(rectF,mPaint);
//
//		// 特别注意：Rect类和RectF类的区别
//		// 精度不同：Rect = int & RectF = float
//
//		// 三种方法画出来的效果是一样的。
//
//		// 以下示例：绘制两个起始角度为0度、扫过90度的圆弧
//// 两者的唯一区别就是是否使用了中心点
//
//		// 绘制圆弧1(无使用中心)
//		RectF rectF1 = new RectF(100, 100, 800,400);
//		// 绘制背景矩形
//		canvas.drawRect(rectF1, mPaint1);
//		// 绘制圆弧
//		canvas.drawArc(rectF1, 0, 90, false, mPaint2);
//
//		// 绘制圆弧2(使用中心)
//		RectF rectF2 = new RectF(100,600,800,900);
//		// 绘制背景矩形
//		canvas.drawRect(rectF2, mPaint1);
//		// 绘制圆弧
//		canvas.drawArc(rectF2,0,90,true,mPaint2);

//		canvas.drawText("BattleCall",300,400,mPaint1);

		// 特别注意：
// 1. 在字符数量较多时，使用会导致卡顿
// 2. 不支持emoji等特殊字符，不支持字形组合与分解
//
//		// 实例
//		canvas.drawPosText("abcde", new float[]{
//				100, 100,    // 第一个字符位置
//				200, 200,    // 第二个字符位置
//				300, 300,    // ...
//				400, 400,
//				500, 500
//		}, mPaint1);
//
//
//
//
//// 数组情况（绘制部分文本）
//		char[] chars = "abcdefg".toCharArray();
//
//		canvas.drawPosText(chars, 1, 3, new float[]{
//				300, 300,    // 指定的第一个字符位置
//				400, 400,    // 指定的第二个字符位置
//				500, 500,    // 指定的第三个字符位置
//
//		}, mPaint1);


//		// 在路径(540,750,640,450,840,600)写上
//		// 1.创建路径对象
//
//		// 2. 设置路径轨迹
//		path.cubicTo(540, 750, 640, 450, 840, 600);
////		// 3. 画路径
////		canvas.drawPicture(mPicture);
//		canvas.drawPath(path,mPaint2);
//		// 4. 画出在路径上的字
//		canvas.drawTextOnPath("天朝陈霸先", path, 50, 0, mPaint2);


//		canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
//		canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),mPicture.getHeight()));


//		https://blog.csdn.net/carson_ho/article/details/60598775

//		canvas.drawBitmap(mBitmap,new Matrix(),mPaint2);

//		canvas.rotate(90);
//		canvas.skew(1f, 0);


//		裁剪共分为：裁剪路径、裁剪矩形、裁剪区域

//// 裁剪路径
//// 方法1
//		public boolean clipPath(@NonNull Path path)
//// 方法2
//		public boolean clipPath(@NonNull Path path, @NonNull Region.Op op)
//
//
//// 裁剪矩形
//// 方法1
//		public boolean clipRect(int left, int top, int right, int bottom)
//// 方法2
//		public boolean clipRect(float left, float top, float right, float bottom)
//// 方法3
//		public boolean clipRect(float left, float top, float right, float bottom,
//		@NonNull Region.Op op)
//
//// 裁剪区域
//// 方法1
//		public boolean clipRegion(@NonNull Region region)
//// 方法2
//		public boolean clipRegion(@NonNull Region region, @NonNull Region.Op op)
		// 为了方便观察,我将坐标系移到屏幕中央
//		canvas.translate(300, 500);
//
//		//原来画布设置为灰色
//		canvas.drawColor(Color.GRAY);
//
//		//第一次裁剪
//		canvas.clipRect(0, 0, 600, 600);
//
//		//将第一次裁剪后的区域设置为红色
//		canvas.drawColor(Color.RED);

		//第二次裁剪,并显示第一次裁剪与第二次裁剪不重叠的区域
//		canvas.clipRect(0, 200, 600, 400, Region.Op.DIFFERENCE);
//		canvas.clipRect(0, 200, 600, 400, Region.Op.INTERSECT);


		//将第一次裁剪与第二次裁剪不重叠的区域设置为黑色
//		canvas.drawColor(Color.BLACK);

//		canvas.drawLine(100,0,100,800,mPaint2);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//			canvas.drawTextRun("我是一颗小小的石头".toCharArray(), 1, 4, 1, 4, 100, 400, false, mPaint2);
//		}


//		canvas.translate(100,100);
//		canvas.drawLine(0,0,200,200,mPaint2);
//		canvas.save();
//		canvas.translate(200,0);
//		canvas.drawLine(0,0,0,-100,mPaint2);
//		canvas.restore();
//		canvas.drawLine(0,0,100,200,mPaint2);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//			canvas.saveLayer(0,0,400,400,mPaint1);
//		}
//		canvas.drawColor(Color.RED);
//		canvas.restore();
//		canvas.drawColor(Color.RED);
//		canvas.drawLine(0,0,200,800,mPaint2);

		canvas.translate(400,200);
		RectF rectF = new RectF(-50,-50,50,50);
		canvas.drawArc(rectF,0,-180,false,mPaint2);
		canvas.drawLine(50,0,300,0,mPaint2);
		canvas.drawLine(300,0,300,200,mPaint2);
		canvas.drawLine(300,200,-300,200,mPaint2);
		canvas.drawLine(-300,200,-300,0,mPaint2);
		canvas.drawLine(-300,0,-50,0,mPaint2);


//		canvas.drawCircle(0,0,50,mPaint2);
//		canvas.clipRect(-200,0,200,100, Region.Op.UNION);
//		canvas.drawColor(Color.RED);

//		canvas.drawArc();

	}



}
