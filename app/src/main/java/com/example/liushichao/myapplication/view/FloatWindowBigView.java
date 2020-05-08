package com.example.liushichao.myapplication.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.mode.WebLinkBean;
import com.example.liushichao.myapplication.utils.DateFormatUtils;
import com.example.liushichao.myapplication.utils.DbHelperDao;
import com.example.liushichao.myapplication.utils.MyWindowManager;


import static android.content.Context.CLIPBOARD_SERVICE;

public class FloatWindowBigView extends LinearLayout {
    private ClipboardManager clipboard = null;
    /**
     * 记录大悬浮窗的宽度------------------
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    public FloatWindowBigView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        Button save =  findViewById(R.id.save);
        Button back =  findViewById(R.id.back);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getClip(context);
            }
        });
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
            }
        });
    }

    /**
     * 从Clip中取数据
     */
    private void getClip(final Context context) {
        clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData.Item item = null;
        //无数据时直接返回
        if (!clipboard.hasPrimaryClip()) {
            return;
        }
        //如果是文本信息
        final String url = clipboard.getText().toString();
        if (!url.isEmpty()) {
            if (url.startsWith("https://") || url.startsWith("http://")) {
                MyWebView myWebView = new MyWebView(context);
                myWebView.loadUrl(url);
                final WebLinkBean bean = new WebLinkBean();
                WebChromeClient wvcc = new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        bean.setTitle(title);
                        bean.setUrl(url);
                        bean.setTime(DateFormatUtils.getDateYYYYMMDDhhmm(System.currentTimeMillis()));
                        bean.setType("default");
                        DbHelperDao.getInstance(context).addUrl(bean);
                    }
                };
                myWebView.setWebChromeClient(wvcc);
            }
        }
    }
}
