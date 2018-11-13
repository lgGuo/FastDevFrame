package com.glg.baselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.glg.baselib.util.LoadingDialogUtil;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {

    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isUseEventBus()) {
            EventBus.getDefault().unregister(this);
        }
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
        LoadingDialogUtil.showProgressDialog(getActivity());

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





}
