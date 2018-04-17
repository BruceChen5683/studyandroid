package www.battlecall.tk.basedemo.moretvmid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdTimeView extends LinearLayout {

    private static final String TAG = "AdTimeView";

    private static final String AD_TEXT = "广告剩余：";
    
    private static final String AD_SEC_TEXT = " 秒";

    private TextView adTextView;

	private Context mContext;
	/**
	 * 倒数计时的数字  1->"01"  10->"10"
	 */
	private String sTime="";	
	private FrameLayout mLayout;
	
	/**默认mLayout 1920*1080 */
	private int layoutWidth = Dimens.UI_BASE_WIDTH;
	private int layoutHeight = Dimens.UI_BASE_HEIGHT;
	
    //小窗使用的布局
	private Rect mRect;
		
	/** 不同屏幕实际广告UI大小 */
    private int mRootWidth = Dimens.ROOT_LAYOUT_WIDTH;
    private int mRootHeight = Dimens.ROOT_LAYOUT_HEIGHT;
    private int mRootMarginTop = Dimens.ROOT_LAYOUT_MARGIN_TOP;
    private int mRootMarginRight = Dimens.ROOT_LAYOUT_MARGIN_RIGHT;
    
    private String productModel = "";
    
    public static final class Dimens {

//        private static final float DEFAULT_DENSITY = 2.0f;
    	
    	/*广告文案素材的高宽*/
    	private static final int UI_BASE_WIDTH = 1920;
    	private static final int UI_BASE_HEIGHT = 1080;


        /** 根布局 PX */
        private static final int ROOT_LAYOUT_WIDTH = 320;//1920
        private static final int ROOT_LAYOUT_HEIGHT = 80;//1080
        private static final int ROOT_LAYOUT_MARGIN_TOP = 60;//1080
        private static final int ROOT_LAYOUT_MARGIN_RIGHT = 90;//1920
        
        /** 文字布局参数 */
//        private static final int AD_LAYOUT_MARGIN = 30;
//        /** 时间 文字布局参数 */
//        private static final int TIME_LAYOUT_WIDTH = 51;
//        private static final int TIME_LAYOUT_MARGIN_LEFT = 10;
        /** 文字大小 */
        private static final int TEXT_SIZE = 36;
//        private static final int TEXT_SIZE_1920_1080 = 18;
//        private static final int TEXT_SIZE_1080_720 = 18;

        
        /** 文字颜色 */
        private static final int TEXT_COLOR = Color.argb(255, 0, 0, 0); //0xffffffff;
        /** 倒计时数字颜色 */
        private static final int TEXT_TIME_COLOR = Color.argb(255, 255, 112, 0); //0xffff7000;
        /** 背景颜色 */
        private static final int BG_COLOR = Color.argb(178, 255, 0, 0); //0xcc000000;
        
    }
    
    public AdTimeView(Context context,FrameLayout layout,Rect rect) {
        super(context);
        mLayout = layout;
        mContext = context;
        mRect = rect;		
        initView();
    }
    
    /**
     * 加载计时View 根布局Layout
     * 
     * @return rootLayout
     */
    private void initView() {
    	/**
    	 * 根据广告素材文案，在不同大小的屏上，等比例缩放 广告 UI
    	 */
        layoutHeight = DisplayUtil.getScreenHeight(mContext);
        layoutWidth = DisplayUtil.getScreenWidth(mContext);
        
        mRootWidth = Dimens.ROOT_LAYOUT_WIDTH*layoutWidth/Dimens.UI_BASE_WIDTH;
        mRootHeight = Dimens.ROOT_LAYOUT_HEIGHT*layoutHeight/Dimens.UI_BASE_HEIGHT;
        mRootMarginTop = Dimens.ROOT_LAYOUT_MARGIN_TOP*layoutHeight/Dimens.UI_BASE_HEIGHT;
        mRootMarginRight = Dimens.ROOT_LAYOUT_MARGIN_RIGHT*layoutWidth/Dimens.UI_BASE_WIDTH;
        Log.d(TAG, "AdTimeView"+"layoutHeight:"+layoutHeight+"-----"+"layoutWidth:"+layoutWidth+ "  mRootWidth:"+mRootWidth+"-----"+"mRootHeight:"+mRootHeight+" mRootMarginTop:"+mRootMarginTop+"-----"+"mRootMarginRight:"+mRootMarginRight);
       	
    	setRootLayoutParams();
    	
		int sdk = Build.VERSION.SDK_INT;
		if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
			this.setBackgroundDrawable(DeviceUtils.getTimerBackground());
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				this.setBackground(DeviceUtils.getTimerBackground());
			}else {
				this.setBackgroundDrawable(DeviceUtils.getTimerBackground());
			}
		}
        this.setBackgroundColor(Dimens.BG_COLOR);
        this.addView(initAdTextLayout(mContext));
        
        
    }
    
    public void release(){
    	if(mLayout != null){
    		mLayout = null;
    	}
    	
    	if(adTextView != null){
    		adTextView = null;
    	}
    	
    }

	
    private void setRootLayoutParams(){
    	FrameLayout.LayoutParams rootParams = null;
    	
		int t_w = mRect.right-mRect.left+1;
		int t_h = mRect.bottom-mRect.top+1;
		int t_x = mRect.left;
		int t_y = mRect.top;
		
        if(t_w <= 0 || t_h <= 0){
    		rootParams = new FrameLayout.LayoutParams(px2dip(mRootWidth),px2dip(mRootHeight));
            rootParams.topMargin = px2dip(mRootMarginTop);
            rootParams.rightMargin = px2dip(mRootMarginRight);
    	}else{
    		rootParams = new FrameLayout.LayoutParams(px2dip(mRootWidth*t_w/layoutWidth),px2dip(mRootHeight*t_h/layoutHeight));
    		rootParams.topMargin = px2dip(mRootMarginTop*t_h/layoutHeight)+t_y;
    		rootParams.rightMargin = px2dip(layoutWidth-(t_x+t_w))+mRootMarginRight*t_w/layoutWidth;
    	}
        rootParams.gravity = Gravity.RIGHT | Gravity.TOP;
    	this.setLayoutParams(rootParams);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER);
    }


    /**
     * 隐藏显示广告计时View
     * 
     * @param show
     */
    public void showView(boolean show) {
        if (show) {
            setVisibility(View.VISIBLE);
        } else {
            setVisibility(View.GONE);
        }
    }

    /**
     * 设置倒计时时间
     * 
     * @param time
     */
    public void setAdTime(int time) {
        if (adTextView != null) {
//            AdLog.d(TAG, "setAdTime", "" + time);
           
            if(time >= 10){
            	sTime = String.valueOf(time);
            }else{
            	sTime = "0"+String.valueOf(time);//使用空格+Num，adText自适应时大小和俩位数有点差异，参考腾讯广告使用"02"
            }
        
            setAdTextViewStyle();
        }
    }
    
    private void setAdTextViewStyle(){
    	if(adTextView == null){
    		return;
    	}
    	SpannableString spannableString = new SpannableString(AD_TEXT+sTime+AD_SEC_TEXT);
        
        ForegroundColorSpan timeBackgroundColorSpan = new ForegroundColorSpan(Dimens.TEXT_TIME_COLOR);
        spannableString.setSpan(timeBackgroundColorSpan, 5, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        adTextView.setText(spannableString);
    }
    
   

    /**
     * 加载"广告剩余："的TextView
     * 
     * @return
     */
    private TextView initAdTextLayout(Context context) {
        adTextView = new TextView(context);
        adTextView.setSingleLine(true);
        reSizeAdTextView();
        LayoutParams adTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        adTextParams.gravity = Gravity.CENTER_VERTICAL;
        adTextView.setGravity(Gravity.CENTER);
        adTextView.setLayoutParams(adTextParams);
        adTextView.setTextColor(Dimens.TEXT_COLOR);
        
        return adTextView;
    }
    
    private int px2dip(int px) {
        float pxValue = px * DisplayUtil.getDensity(mContext);
        return DisplayUtil.px2dip(mContext, pxValue);
    }
    
    public void setBoundary(int x, int y, int w, int h){
        mRect = new Rect(x, y, x + w - 1, y + h - 1);
        setRootLayoutParams();
		reSizeAdTextView();	
    }
          

	/**
	 * 
	 * @param tv
	 * @param maxWidth
	 * @param text
	 */
	private void adjustTvTextSize(TextView tv, int maxWidth, String text) {
		if(tv == null){
			return;
		}
        int avaiWidth = maxWidth - tv.getPaddingLeft() - tv.getPaddingRight() - 10;

        if (avaiWidth <= 0) {
            return;
        }

        TextPaint textPaintClone = new TextPaint(tv.getPaint());
        // note that Paint text size works in px not sp
        float trySize = textPaintClone.getTextSize();

        while (textPaintClone.measureText(text) > avaiWidth) {
            trySize--;
            textPaintClone.setTextSize(trySize);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
    }
        
    private void reSizeAdTextView() {
    	productModel = DeviceUtils.getModel();
    	if(adTextView == null){
    		return;
    	}
		int t_w = mRect.right-mRect.left+1;
		int t_h = mRect.bottom-mRect.top+1;
    	if(t_w <= 0 || t_h <= 0){//全屏
			adTextView.setTextSize(DisplayUtil.npx2sp(mContext, Dimens.TEXT_SIZE*(((float)layoutWidth/(float) Dimens.UI_BASE_WIDTH))));

//       		if(layoutWidth == Dimens.UI_BASE_WIDTH/* && layoutHeight == Dimens.UI_BASE_HEIGHT*/){
//    			adTextView.setTextSize(DisplayUtil.px2sp(mContext, Dimens.TEXT_SIZE));
//    			Log.d(TAG, "reSizeAdTextView"+"DisplayUtil.px2sp(mContext, Dimens.TEXT_SIZE) "+DisplayUtil.px2sp(mContext, Dimens.TEXT_SIZE));
//    		}else if(layoutWidth == 1280/* && layoutHeight == 720*/){
//
//    			//哎  TODO自适应
//    			if(productModel.equals("mygicaTVbox")){//美如画
//    				adTextView.setTextSize(DisplayUtil.px2sp(mContext, 18));
//    			}else{
//    				adTextView.setTextSize(DisplayUtil.px2sp(mContext, 24));
//    			}
//    		}else{//后续需要不同分辨率大小的textSize大小适配
//    			adjustTvTextSize(adTextView, px2dip(mRootWidth), AD_TEXT+"88"+AD_SEC_TEXT);
//    		}
            
    	}else{
    		adjustTvTextSize(adTextView, px2dip(mRootWidth*t_w/layoutWidth), AD_TEXT+"88"+AD_SEC_TEXT);
    	}
	}

    
}
