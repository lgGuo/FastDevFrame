package com.glg.baselib.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.githang.statusbar.StatusBarCompat;
import com.glg.baselib.R;
import com.glg.baselib.util.AppManager;
import com.glg.baselib.util.LoadingDialogUtil;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public abstract class BaseMvpActivity<V extends MvpView,P extends MvpBasePresenter<V>>
        extends MvpActivity<V,P> implements  MvpView, BGASwipeBackHelper.Delegate{
    private BGASwipeBackHelper mSwipeBackHelper;
    /**
     * 获取布局资源文件
     */
    public abstract int getLayoutID();

    /**
     * 初始化控件
     */

    public abstract void initViews();


    /**
     * 是否需要用EventBus,默认为false,继承此activit
     * 如果需要用酒复写此方法，返回true
     *
     */
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutID());
        initViews();

        AppManager.getAppManager().addActivity(this);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，
     * 如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isUseEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        AppManager.getAppManager().finishActivity(this);
    }


    /**
     * 显示loading
     * @param target 由哪个view而触发的loading,防止同一控件快速点击
     */
    public void showLoading(View target){
        if (target!=null){
            target.setClickable(false);
            target.setEnabled(false);
        }
        LoadingDialogUtil.showProgressDialog(this);

    }



    /**
     * 关闭loading
     * @param target 由哪个view而触发的loading
     */
    public void hideLoading( View target){
        if (target!=null){
            target.setClickable(true);
            target.setEnabled(true);
        }

        LoadingDialogUtil.closeProgressDialog();
    }


    /**
     * 浅色状态栏
     */
    protected void setLightStatusBar(boolean lightMode){
        if(Build.VERSION.SDK_INT <=22){
            StatusBarCompat.setStatusBarColor(this, Color.parseColor("#9f999999"), false);
        }else {
            StatusBarCompat.setStatusBarColor(this, Color.TRANSPARENT, lightMode);
        }
    }


    /**
     * 透明状态栏
     * @param offsetView 需要偏移的view,防止view顶到状态栏上
     */
    protected void setTansparentStatusBar(View offsetView){
        if(Build.VERSION.SDK_INT <=22){
            StatusBarUtil.setTranslucentForImageViewInFragment(this,0,offsetView);

        }else {
            StatusBarUtil.setTranslucentForImageView(this,0,offsetView);

        }
    }
}
