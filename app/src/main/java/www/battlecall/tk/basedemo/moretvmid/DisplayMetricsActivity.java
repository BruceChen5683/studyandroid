package www.battlecall.tk.basedemo.moretvmid;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.FrameLayout;

import www.battlecall.tk.basedemo.R;

public class DisplayMetricsActivity extends AppCompatActivity {

	private FrameLayout mLayout;
	private AdTimeView mAdTimeView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.activity_display_metrics,null);
		Log.d("", "onCreate: mLayout "+mLayout);
		setContentView(mLayout);
		mAdTimeView = new AdTimeView(this,mLayout,new Rect(2,2,1,1));
		mLayout.addView(mAdTimeView);
		mAdTimeView.setAdTime(45);
	}
}
