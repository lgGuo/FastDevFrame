package com.glg.baselib.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.glg.baselib.R;
import com.glg.baselib.base.BaseApplication;
import com.glg.baselib.widget.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 *  Created by glg_d on 2017/11/14.
 */

public class LoadingDialogUtil {


    public static void showProgressDialog(Activity activity){
        showProgressDialog(activity,true,true);
    }

    private static WeakReference<Activity> mWeakReference;
    private static LoadingDialog mProgressDialog;

    /**
     * @param activity 需要弹窗的activity
     * @param flag 触摸弹窗外区域，是否取消窗口
     * */
    public static LoadingDialog showProgressDialog(Activity activity, boolean isShow, boolean flag){


        if(mWeakReference == null){
            mWeakReference = new WeakReference(activity);
        }

        activity = mWeakReference.get();

        if (mProgressDialog == null) {
            if (activity.getParent() != null) {
                mProgressDialog = new LoadingDialog(activity.getParent());
            } else {
                mProgressDialog = new LoadingDialog(activity);
            }
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);

        }

        if (!mProgressDialog.isShowing()&&isShow&&isLiving(activity)) {
            mProgressDialog.show();
        }

        return mProgressDialog;
    }

    /**
     * 关闭进度框
     */
    public static void closeProgressDialog() {

        if (isShowing(mProgressDialog) && isExist_Living(mWeakReference)) {

            if (isShowing(mProgressDialog)) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
                mWeakReference.clear();
                mWeakReference = null;
            }
        }


    }

    /**
     * 判断进度框是否正在显示
     */
    private static boolean isShowing(LoadingDialog dialog) {
        return  dialog != null&& dialog.isShowing();
    }

    /**
     * 判断activity是否存活
     * */
    private static boolean isLiving(Activity activity){

        if (activity == null) {
            return false;
        }

        if (activity.isFinishing()) {
            return false;
        }

        return true;
    }


    private static boolean isExist_Living(WeakReference<Activity> weakReference) {

        if(weakReference != null){
            Activity activity = weakReference.get();
            if (activity == null) {
                return false;
            }

            if (activity.isFinishing()) {
                return false;
            }
            return true;
        }

        return false;
    }



    public static  void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseApplication.getMyApplication());
        LayoutInflater inflater = LayoutInflater.from(BaseApplication.getMyApplication());
        View v = inflater.inflate(R.layout.dialog_loading, null);
        final Dialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setContentView(v);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = SystemUtil.dp2px(BaseApplication.getMyApplication(),120);
        params.height = SystemUtil.dp2px(BaseApplication.getMyApplication(),100);
        params.gravity = Gravity.CENTER;
        params.dimAmount = 0;
        dialog.setCanceledOnTouchOutside(false);
        window.setAttributes(params);

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.show();
    }
}
