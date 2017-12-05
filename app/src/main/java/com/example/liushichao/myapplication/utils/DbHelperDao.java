package com.example.liushichao.myapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.liushichao.myapplication.mode.WebLinkBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushichao on 2017/11/30.
 */

public class DbHelperDao {
    private static final String HTML_TABLE = "HTML";
    private static final String HTML_TYPE_TABLE = "HTML_TYPE";
    private static DbHelperDao INSTANCE;
    private DbHelper dbHelper;

    private DbHelperDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public synchronized static DbHelperDao getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DbHelperDao(context);
        }
        return INSTANCE;
    }

    /**
     * 添加网页
     */
    public void addUrl(WebLinkBean webLinkBean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        WebLinkBean item = getHtmlItem(webLinkBean.getUrl());
        if (item == null) {
            ContentValues values = toHtmlContentValues(webLinkBean);
            db.insert(HTML_TABLE, null, values);
        }
    }

    /**
     * 修改指定网页分类
     */
    public void upDateUrl(WebLinkBean webLinkBean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = toHtmlContentValues(webLinkBean);
        db.update(HTML_TABLE, values, "url=?", new String[]{webLinkBean.getUrl()});
    }

    /**
     * 添加分类
     */
    public void addHtmlType(String html_type) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String item = getHtmlTypeItem(html_type);
        if (item.isEmpty()) {
            ContentValues values = toHtmlTypeContentValues(html_type);
            db.insert(HTML_TYPE_TABLE, null, values);
        }
    }

    /**
     * 删除网页
     */
    public void removeHtml(String url) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(HTML_TABLE, "url=?", new String[]{url});
    }

    /**
     * 删除分类
     */
    public void removeHtmlType(String html_type) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(HTML_TYPE_TABLE, "type=?", new String[]{html_type});
    }

    /**
     * 查找所有type
     */
    public List<String> getHtmlTypeItems() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HTML_TYPE ", null);
        List<String> items = new ArrayList<>(cursor.getCount());
        try {
            while (cursor.moveToNext()) {
                items.add(toHtmlTypeItem(cursor));
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    /**
     * 查找指定类型的网页
     */
    public List<WebLinkBean> getByTypeHtmlItem(String type) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HTML where type = ?", new String[]{type});
        List<WebLinkBean> items = new ArrayList<>(cursor.getCount());
        try {
            while (cursor.moveToNext()) {
                items.add(toHtmlItem(cursor));
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    /**
     * 查找所有网页
     */
    public List<WebLinkBean> getHtmlItems() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HTML order by id desc", null);
        List<WebLinkBean> items = new ArrayList<>(cursor.getCount());
        try {
            while (cursor.moveToNext()) {
                items.add(toHtmlItem(cursor));
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    /**
     * 查找指定url
     */
    public WebLinkBean getHtmlItem(String Url) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HTML where url = ?", new String[]{Url});
        try {
            if (cursor.moveToNext()) {
                return toHtmlItem(cursor);
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    /**
     * 查找指定type
     */
    public String getHtmlTypeItem(String html_type) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HTML_TYPE where type = ?", new String[]{html_type});
        try {
            if (cursor.moveToNext()) {
                return toHtmlTypeItem(cursor);
            }
        } finally {
            cursor.close();
        }
        return "";
    }

    @NonNull
    private WebLinkBean toHtmlItem(Cursor cursor) {
        WebLinkBean item = new WebLinkBean();
        item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        item.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        item.setTime(cursor.getString(cursor.getColumnIndex("time")));
        item.setType(cursor.getString(cursor.getColumnIndex("type")));
        return item;
    }

    @NonNull
    private String toHtmlTypeItem(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex("type"));
    }

    @NonNull
    private ContentValues toHtmlContentValues(WebLinkBean webLinkBean) {
        ContentValues values = new ContentValues();
        if (!webLinkBean.getTitle().isEmpty()) {
            values.put("title", webLinkBean.getTitle());
        }
        if (!webLinkBean.getUrl().isEmpty()) {
            values.put("url", webLinkBean.getUrl());
        }
        if (!webLinkBean.getTime().isEmpty()) {
            values.put("time", webLinkBean.getTime());
        }
        if (!webLinkBean.getType().isEmpty()) {
            values.put("type", webLinkBean.getType());
        }
        return values;
    }

    @NonNull
    private ContentValues toHtmlTypeContentValues(String type) {
        ContentValues values = new ContentValues();
        values.put("type", type);
        return values;
    }

}
