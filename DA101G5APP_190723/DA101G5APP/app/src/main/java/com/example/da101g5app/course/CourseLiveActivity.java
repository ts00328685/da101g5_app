package com.example.da101g5app.course;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.da101g5app.R;

public class CourseLiveActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_live);

        WebView wv = findViewById(R.id.act_course_live_web_view);
//        wv.getSettings().setLoadWithOverviewMode(true);
//        wv.getSettings().setUseWideViewPort(true);
//        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setMediaPlaybackRequiresUserGesture(false);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setDomStorageEnabled(true);
//        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wv.getSettings().setAllowFileAccessFromFileURLs(true);
//        wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {

                CourseLiveActivity.this.runOnUiThread(new Runnable(){
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }// run
                });// MainActivity

            }// onPermissionRequest

        });
        WebViewClient mWebClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }



        };

        wv.setWebViewClient(mWebClient);


        wv.loadUrl("https://192.168.197.4:8083/");
//        wv.loadUrl("https://youtube.com");


    }

    public void findView(){
    }
}
