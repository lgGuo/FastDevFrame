package com.glg.baselib.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.glg.baselib.R;
import com.glg.baselib.util.SystemUtil;


public class LoadingDialog extends Dialog {

    public Context mContext;
    private LayoutInflater inflater;
    private TextView loadtext;
    public boolean isClose;

    public LoadingDialog(final Context context) {
        super(context, R.style.MyLoadDialog);
        this.mContext = context;
        inflater = (LayoutInflater) mContext .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_loading, null);
        loadtext = (TextView) layout.findViewById(R.id.loading_title);
        setContentView(layout);


        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = SystemUtil.dp2px(context,120);
        params.height = SystemUtil.dp2px(context,100);
        params.gravity = Gravity.CENTER;
        params.dimAmount = 0;
        this.setCanceledOnTouchOutside(false);
        window.setAttributes(params);
        
    }

    /**
     * 设置显示文字
     * 默认不显示文字
     *
     * @param content 文字内容
     */
    public void setLoadText(String content) {
        loadtext.setVisibility(View.VISIBLE);
        loadtext.setText(content);
    }

    /**
     * 限制反回键盘关闭进度条
     *
     * @param close
     */
    public void setColose(boolean close) {
        this.isClose = close;
        if (!isClose) {
            setCancelable(isClose);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog,
                                     int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH) {

                        dismiss();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {

        }

    }
}