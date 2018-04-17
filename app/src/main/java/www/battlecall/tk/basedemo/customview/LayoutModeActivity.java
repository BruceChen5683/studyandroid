package www.battlecall.tk.basedemo.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.battlecall.tk.basedemo.R;
//clipBounds   opticalBounds 后者消除空白间隙
public class LayoutModeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_mode);
	}
}
