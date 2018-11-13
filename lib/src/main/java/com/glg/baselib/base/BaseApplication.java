package com.glg.baselib.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.multidex.MultiDex;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import com.glg.baselib.BuildConfig;
import com.glg.baselib.R;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.weavey.loading.lib.LoadingLayout;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        MMKV.initialize(this);


        FileDownloader.init(this);
        initLoadLayout();

        BGASwipeBackHelper.init(this,  null);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }




    // 获取Application
    public static Context getMyApplication() {
        return instance;
    }


    private void initLoadLayout(){
        LoadingLayout.getConfig()
                .setLoadingPageLayout(R.layout.base_loading)
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.drawable.load_error)
                .setAllPageBackgroundColor(R.color.transparent)
                .setAllTipTextColor(R.color.tips)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.tips)
                .setReloadButtonWidthAndHeight(150,40);
    }
}
