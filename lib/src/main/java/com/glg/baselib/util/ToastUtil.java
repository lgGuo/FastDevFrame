package com.glg.baselib.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.glg.baselib.R;

public class ToastUtil {
    private static Toast toast   = null;

    public static void show(Context context, String msg){

        View view;
        TextView tvMsg;
        if(toast==null){
            toast = new Toast(context.getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            tvMsg =  view.findViewById(R.id.tv_message);
            tvMsg.setText(msg);
            toast.setView(view);

        }else {
            view = toast.getView();
            tvMsg = view.findViewById(R.id.tv_message);
            tvMsg.setText(msg);
        }


        toast.show();

    }
}
