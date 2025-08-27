package com.reactnativedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.facebook.react.CustomReactFragment
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.runtime.ReactSurfaceView

class MainActivity : FragmentActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val reactNativeFragment = CustomReactFragment.Builder()
                .setComponentName("ReactNativeDemo")
                .setLaunchOptions(getLaunchOptions("test message"))
                .build()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.reactNativeFragment, reactNativeFragment)
                .commit()
            button.visibility = View.GONE
        }
    }

    override fun setContentView(view: View?) {
        if (view is ReactSurfaceView) {
            return
        }
        super.setContentView(view)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    private fun getLaunchOptions(message: String) = Bundle().apply {
        putString("message", message)
    }
}
