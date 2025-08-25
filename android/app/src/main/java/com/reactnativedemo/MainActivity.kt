package com.reactnativedemo

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.facebook.react.ReactFragment
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler

class MainActivity : FragmentActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val reactNativeFragment = ReactFragment.Builder()
                .setComponentName("HelloWorld")
                .setLaunchOptions(getLaunchOptions("test message"))
                .build()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.reactNativeFragment, reactNativeFragment)
                .commit()
        }
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    private fun getLaunchOptions(message: String) = Bundle().apply {
        putString("message", message)
    }
}
