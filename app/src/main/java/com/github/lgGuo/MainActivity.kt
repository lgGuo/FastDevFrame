package com.github.lgGuo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.glg.baselib.util.SystemUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemUtil.gotoAppDetail(this)

    }
}
