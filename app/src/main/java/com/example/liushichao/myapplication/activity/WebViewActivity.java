package com.example.liushichao.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.view.MyWebView;

/**
 * Created by liushichao on 2017/11/28.
 */

public class WebViewActivity extends Activity {



    



    private MyWebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);
        /*webView.loadDataWithBaseURL(null, getIntent().getStringExtra("data"), "text/html" , "utf-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(WebViewActivity.this,view.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });*/

        //WebSettings websetting = webView.getSettings();
        String url = getIntent().getStringExtra("data");

        if (!url.isEmpty()) {
            webView.loadUrl(url);
        }
        //webView.loadUrl("https://www.hao123.com/?tn=95470842_hao_pg");
    }
}
