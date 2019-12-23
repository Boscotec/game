package com.boscotec.game.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.boscotec.game.R;

public class WebActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_REQUEST_TYPE = "request_type";
    public static final String EXTRA_POST_DATA = "post_data";
    private String postData;
    private String url;
    private String request = "GET";

    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        webView = findViewById(R.id.webview);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey(EXTRA_URL)) {
            url = bundle.getString(EXTRA_URL);
        }

        if (bundle.containsKey(EXTRA_REQUEST_TYPE)) {
            request = bundle.getString(EXTRA_REQUEST_TYPE);
            postData = bundle.getString(EXTRA_POST_DATA);
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClientImpl(this));
        webView.setWebChromeClient(new WebChromeClient());

//        if (TextUtils.equals(request, "POST")) {
//            try {
//                //webView.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
//            }catch (Exception e){
//              e.printStackTrace();
//            }
//        } else{
            webView.loadUrl(url);
//        }
    }

    public void showProgress() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(true);
    }

    public void hideProgress() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    public void successful(){
        Intent intent = new Intent();
        intent.putExtra("result", "Transaction successful...");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
