package com.example.liushichao.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by liushichao on 2017/11/30.
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
        initStting(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStting(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStting(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initStting(context);
    }

    public void initStting(Context context) {
        WebSettings websetting = getSettings();
        websetting.setDomStorageEnabled(true);    //开启DOM形式存储
        websetting.setDatabaseEnabled(true);   //开启数据库形式存储
        String appCacheDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();   //缓存数据的存储地址
        websetting.setAppCachePath(appCacheDir);
        websetting.setAppCacheEnabled(true);  //开启缓存功能
        websetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);      //缓存模式
        websetting.setAllowFileAccess(true);
        websetting.setBuiltInZoomControls(true);
        websetting.setUseWideViewPort(true);
        websetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        websetting.setLoadWithOverviewMode(true);
        setInitialScale(25);
        setWebViewClient(wvc);
    }

    WebViewClient wvc = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
            MyWebView.this.loadUrl(url);
            // 消耗掉这个事件。Android中返回True的即到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
            return true;
        }
    };
}
