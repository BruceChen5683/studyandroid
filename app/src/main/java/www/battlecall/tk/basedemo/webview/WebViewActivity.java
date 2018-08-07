package www.battlecall.tk.basedemo.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

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

		mWebView.loadUrl("http://www.baidu.com/");
////		//设置不用系统浏览器打开,直接显示在当前Webview
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("cjl", "WebViewActivity ---------shouldOverrideUrlLoading:      "+url);
				view.loadUrl(url);
//				view.loadUrl("http://www.hao123.com");
				return true;
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
