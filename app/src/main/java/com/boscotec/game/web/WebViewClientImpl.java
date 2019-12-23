package com.boscotec.game.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewClientImpl extends WebViewClient {
    private WebActivity activity;

    public WebViewClientImpl(WebActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        activity.showProgress();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        activity.hideProgress();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Toast.makeText(activity.getBaseContext(), "Error: "+description, Toast.LENGTH_LONG).show();
        activity.hideProgress();
        if(view.canGoBack()) view.goBack();
        else activity.onBackPressed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //https://moneybag.ng/dashboard/savings/fast-save-newcard/?stat=vconfirm
        //https://moneybag.ng/confirm/account-activation/

//        if(url.startsWith(Constants.PAYMENT_DONE)){
//            activity.successful();
//            return true;
//        }

       // String last = Uri.parse(url).getLastPathSegment();
       // if(TextUtils.equals(last, "account-activation") ||  TextUtils.equals(last, "fast-save-newcard")){
       //     activity.successful();
       //     return true;
       // }

        //if(Uri.parse(url).getHost().endsWith(Constants.HOST)){
        //    activity.successful();
        //    return true;
        //}

        //if(Uri.parse(url).getHost().endsWith(Constants.HOST)) {
        //    return false;
       // }

        //if(Uri.parse(url).getHost().length() == 0) {
        //    return false;
        //}

       // if(url.startsWith(Constants.PAYMENT) || url.startsWith(Constants.ABOUT_US) || url.startsWith(Constants.FAQ)){
       //     return false;
       // }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        //activity.startActivity(intent);
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return super.shouldInterceptRequest(view, url);
    }
}
