package www.battlecall.tk.basedemo.webview;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;

import www.battlecall.tk.basedemo.R;

public class WebViewActivity extends AppCompatActivity {

	private WebView mWebView;
	private WebSettings mWebSettings;
	private TextView beginLoading,endLoading,loading,mtitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);

		mWebView = findViewById(R.id.webView1);
		beginLoading = (TextView) findViewById(R.id.text_beginLoading);
		endLoading = (TextView) findViewById(R.id.text_endLoading);
		loading = (TextView) findViewById(R.id.text_Loading);
		mtitle = (TextView) findViewById(R.id.title);

		mWebSettings = mWebView.getSettings();
		mWebSettings.setJavaScriptEnabled(true);
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);


//		mWebView.loadUrl("http://www.baidu.com/");
//		mWebView.loadUrl("file:///android_asset/js.html");
//		mWebView.addJavascriptInterface(new AndroidtoJS(),"test");
		mWebView.loadUrl("file:///android_asset/callAndroidJs.html");
////		//设置不用系统浏览器打开,直接显示在当前Webview
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("cjl", "WebViewActivity ---------shouldOverrideUrlLoading:      "+url);
//				view.loadUrl(url);
////				view.loadUrl("http://www.hao123.com");
				if (url.equals("js://webview?arg1=111&arg2=222")){
					Uri uri = Uri.parse(url);
					// 如果url的协议 = 预先约定的 js 协议
					// 就解析往下解析参数
					if (uri.getScheme().equals("js")){
						if(uri.getAuthority().equals("webview")){
							Log.d("cjl", "WebViewActivity ---------shouldOverrideUrlLoading:      js调用android方法2");
							// 可以在协议上带有参数并传递到Android上
							HashMap<String, String> params = new HashMap<>();
							Set<String> collection = uri.getQueryParameterNames();
						}
						return true;
					}
				}
				return super.shouldOverrideUrlLoading(view,url);
			}

		});

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("cjl", "WebViewActivity ---------onClick:      ");
				mWebView.post(new Runnable() {
					@Override
					public void run() {
							if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
								mWebView.evaluateJavascript("javascript:myJS()", new ValueCallback<String>() {
									@Override
									public void onReceiveValue(String value) {

									}
								});
							}else {
								mWebView.loadUrl("javascript:myJS()");
							}
					}
				});

			}
		});



//		mWebView.setWebChromeClient(new WebChromeClient(){
//			@Override
//			public void onReceivedTitle(WebView view, String title) {
//				Log.d("cjl", "WebViewActivity ---------onReceivedTitle:      ");
//				mtitle.setText(title);
//			}
//
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//				if (newProgress < 100) {
//					String progress = newProgress + "%";
//					loading.setText(progress);
//				} else if (newProgress == 100) {
//					String progress = newProgress + "%";
//					loading.setText(progress);
//				}
//			}
//
//			@Override
//			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//				Log.d("cjl", "WebViewActivity ---------onJsAlert:      "+message);
//				AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
//				b.setTitle("Alert");
//				b.setMessage(message);
//				b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						result.confirm();
//					}
//				});
//				b.setCancelable(false);
//				b.create().show();
//				return true;
//			}
//
//		});


//		mWebView.setWebViewClient(new WebViewClient(){
//			@Override
//			public void onPageStarted(WebView view, String url, Bitmap favicon) {
//				super.onPageStarted(view, url, favicon);
//				Log.d("cjl", "WebViewActivity ---------onPageStarted:      ");
//				beginLoading.setText("start loading");
//			}
//
//			@Override
//			public void onPageFinished(WebView view, String url) {
//				super.onPageFinished(view, url);
//				Log.d("cjl", "WebViewActivity ---------onPageFinished:      ");
//				endLoading.setText("end loading");
//			}
//		});
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		if (mWebView != null){
			mWebView.loadDataWithBaseURL(null,"","text/html","utf-8",null);
			mWebView.clearHistory();

			((ViewGroup)mWebView.getParent()).removeView(mWebView);
			mWebView.destroy();
			mWebView = null;
		}
		super.onDestroy();
	}
}
//TODO https://blog.csdn.net/carson_ho/article/details/64904691
//TODO  方式3：通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息

//TODO https://www.jianshu.com/p/3a345d27cd42  漏洞测试
