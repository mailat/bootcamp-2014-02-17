package in.wptrafficanalyzer.webviewindeterminateprogress;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	WebView mWvUrl;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		final Activity activity = this;

		super.onCreate(savedInstanceState);

		/** Enabling Progress bar for this activity */
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_main);

		mWvUrl = (WebView) findViewById(R.id.wv_url);

		/** Enabling Javascript for the WebView */
		mWvUrl.getSettings().setJavaScriptEnabled(true);

		OnClickListener btnLoadClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {

				/**
				 * Getting reference to edittext widget of the layout
				 * activity_main.xml
				 * */
				EditText etUrl = (EditText) findViewById(R.id.et_url);

				/** Load the url entered in the edittext box */
				mWvUrl.loadUrl(etUrl.getText().toString());

				/** Showing Indeterminate progress bar in the title bar */
				activity.setProgressBarIndeterminateVisibility(true);

			}
		};

		Button btnLoad = (Button) findViewById(R.id.btn_load);
		btnLoad.setOnClickListener(btnLoadClickListener);

		mWvUrl.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				/** This prevents the loading of pages in system browser */
				return false;
			}

			/** Callback method, executed when the page is completely loaded */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);

				Toast.makeText(getBaseContext(), "Page loaded Completely",
						Toast.LENGTH_SHORT).show();

				/** Hiding Indeterminate Progress Bar in the title bar */
				activity.setProgressBarIndeterminateVisibility(false);

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
