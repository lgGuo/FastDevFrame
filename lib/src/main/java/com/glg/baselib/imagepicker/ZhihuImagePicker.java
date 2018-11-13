package com.glg.baselib.imagepicker;

import android.content.Context;

import com.qingmei2.rximagepicker.entity.Result;
import com.qingmei2.rximagepicker.entity.sources.Camera;
import com.qingmei2.rximagepicker.entity.sources.Gallery;
import com.qingmei2.rximagepicker.ui.ICustomPickerConfiguration;
import com.qingmei2.rximagepicker_extension_zhihu.ui.ZhihuImagePickerActivity;

import io.reactivex.Observable;

public interface ZhihuImagePicker {

    @Gallery(componentClazz = ZhihuImagePickerActivity.class,openAsFragment = false)
    Observable<Result> openGalleryAsNormal(Context context, ICustomPickerConfiguration config);

    @Camera
    Observable<Result> openCamera(Context context);

}
