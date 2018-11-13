package com.glg.baselib.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

import java.util.Stack;


/**
 * activity栈管理工具
 */
public class AppManager {


	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager(){}
	/**
	 * 单一实例
	 */
	public static AppManager getAppManager(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){

		if(activityStack==null){
			return null;
		}

		if (activityStack.size()>0){
			Activity activity=activityStack.lastElement();
			return activity;
		}else {
			return null;
		}

	}
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity(){
		if(activityStack==null){
			return ;
		}
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activityStack==null){
			return ;
		}

		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
		}
	}
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		if(activityStack==null){
			return ;
		}
		Log.e("activityStack.size()",activityStack.size()+"");
		for (int i = 0, size = activityStack.size(); i < size; i++){
			if (null != activityStack.get(i)){
				Log.e("activityStack",activityStack.get(i).getClass().getName());
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 除了指定Activity，结束所有Activity
	 */
	public void finishAllActivityExcept(Class<?> cls){
		if(activityStack==null){
			return ;
		}
		Log.e("activityStack",activityStack.getClass().getSimpleName()+"");
		for (int i = 0, size = activityStack.size(); i < size; i++){
			if (null != activityStack.get(i)&&!activityStack.get(i).getClass().equals(cls)){
				Log.e("activityStack",activityStack.get(i).getClass().getName());
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}


	/**
	 * 切换账号
	 */
	public void changeAccount() {
		MMKV.defaultMMKV().clear();
	}

}