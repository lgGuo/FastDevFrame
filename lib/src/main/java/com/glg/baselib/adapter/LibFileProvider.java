package com.glg.baselib.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;


/**
 * 安卓7.0 fileprovider 适配辅助类
 */
public class LibFileProvider {

    public static Uri getUriForFile(Context context, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static Uri getUriForFile24(Context context, File file) {
        return android.support.v4.content.FileProvider.getUriForFile(context,
                context.getPackageName() + ".lib.fileProvider",
                file);
    }


    public static void setIntentDataAndType(Context context, Intent intent, String type, File file, boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}
