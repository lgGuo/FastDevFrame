/*
 * Copyright (C) 2015-2016 Jacksgong(blog.dreamtobe.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.glg.baselib.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * Created by Jacksgong on 15/7/6.
 */
public class KeyboardUtil {

    /**
     * 针对给定的editText显示软键盘（editText会先获得焦点）. 可以和{@link #hideKeyboard(View)}
     * 搭配使用，进行键盘的显示隐藏控制。
     */

    public static void showKeyboard(final EditText editText) {
        if (null == editText)
            return;

        if (!editText.requestFocus()) {
            Log.w("KeyboardUtil", "showSoftInput() can not get focus");
            return;
        }

        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 200);
    }

    /**
     * 隐藏软键盘
     * @param view 当前页面上任意一个可用的view
     */
    public static boolean hideKeyboard(final View view) {
        if (null == view)
            return false;

        InputMethodManager inputManager = (InputMethodManager) view.getContext().getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // 即使当前焦点不在editText，也是可以隐藏的。
        assert inputManager != null;
        return inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }




}
