package www.battlecall.tk.basedemo.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by BattleCall on 2018/8/7.
 */

public class AndroidtoJS{

	@JavascriptInterface
	public void hello(String msg){
		Log.d("cjl", "AndroidtoJS ---------hello:      ");
	}
}
