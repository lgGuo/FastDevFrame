package com.glg.baselib.util.glide;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

public class ImageUtil {

    public static void dispalyImage(Context context,String url, ImageView imageView){
        GlideApp.with(context).load(url).centerCrop().into(imageView);
    }


    public static void dispalyImage(Context context, Uri uri, ImageView imageView){
        GlideApp.with(context).load(uri).centerCrop().into(imageView);
    }


    public static void dispalyImage(Context context,String url, ImageView imageView,@DrawableRes int placeHolder){
        GlideApp.with(context).load(url).centerCrop().placeholder(placeHolder).into(imageView);
    }


    public static void dispalyImage(Context context,String url, ImageView imageView,@DrawableRes int placeHolder,int radius){

        RequestOptions options = new RequestOptions().centerCrop().placeholder(placeHolder).transform(new GlideRoundTransform(context,radius));

        GlideApp.with(context).load(url).apply(options).into(imageView);
    }

}
