package com.glg.baselib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import com.glg.baselib.adapter.LibFileProvider;
import com.glg.baselib.base.BaseApplication;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

public class SystemUtil {


    /**
     * 获取版本号
     * @param context  上下文
     * @return 版本号
     */
    public static int getAppVersionCode(Context context){
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }



    /**
     * 获取版本名
     * @return 版本号
     */
    public static String getAppVersionName(){
        String versionname = "v1.0.0";
        try {
            // ---get the package info---
            Context myApplication = BaseApplication.getMyApplication();
            PackageManager pm = myApplication.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(myApplication.getPackageName(), 0);
            versionname = pi.versionName;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionname;
    }


    /**
     * 获取屏幕宽
     * @param context 上下文
     * @return 屏幕宽
     */
    public static int getScreenWidth(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (manager!=null){
            manager.getDefaultDisplay().getMetrics(outMetrics);
            return  outMetrics.widthPixels;
        }
        return  0;

    }



    /**
     * dp转px
     */
    public static int dp2px(Context context,int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }



    /**
     * dp转px
     */
    public static int getNetWorkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getMyApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase(Locale.getDefault()).equals("cmnet")) {
                    netType = 0;
                } else {
                    netType = 1;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 2;
        }
        return netType;
    }



    /**
     * 安装APK
     * @param context
     * @param file
     */
    public static void installApk(Context context,File file) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (context.getPackageManager().canRequestPackageInstalls()){
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                LibFileProvider
                        .setIntentDataAndType(context,intent
                                ,"application/vnd.android.package-archive",file,true);
                context.startActivity(intent);
            }else {
                Uri packageURI = Uri.parse("package:" + context.getPackageName());
                //注意这个是8.0新API
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                context.startActivity(intent);
            }

        } else {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            LibFileProvider
                    .setIntentDataAndType(context,intent
                            ,"application/vnd.android.package-archive",file,true);
            context.startActivity(intent);
        }


    }


    /**
     * 判断是否有虚拟底部按钮
     *
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
           Logger.e("SystemUtil", e);
        }
        return hasNavigationBar;
    }

    /**
     * 获取底部虚拟按键高度
     *
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }


    public static void gotoAppDetail(Context context){
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package",context.getPackageName(), null));
        context.startActivity(localIntent);

    }


    /**
     * 拨打电话
     * @param context
     * @param phone
     */
    @SuppressLint("MissingPermission")
    public static void makePhone(Context context, String phone){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }





}
