package com.facebook.react

import android.os.Bundle
import com.facebook.react.config.ReactFeatureFlags

class CustomReactFragment : ReactFragment() {

    private val reactHost: ReactHost?
        get() = (activity?.application as ReactApplication?)?.reactHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            var mainComponentName: String? = null
            var launchOptions: Bundle? = null

            arguments?.let { arguments ->
                mainComponentName = arguments.getString(ARG_COMPONENT_NAME)
                launchOptions = arguments.getBundle(ARG_LAUNCH_OPTIONS)
            }

            mReactDelegate =
                ReactDelegate(activity, reactHost, mainComponentName, launchOptions)
        }
    }

    class Builder : ReactFragment.Builder() {
        override fun build(): CustomReactFragment = newInstance(mComponentName, mLaunchOptions, mFabricEnabled)
    }

    companion object {
        private fun newInstance(
            componentName: String?,
            launchOptions: Bundle?,
            fabricEnabled: Boolean?,
        ): CustomReactFragment {
            val args =
                Bundle().apply {
                    putString(ARG_COMPONENT_NAME, componentName)
                    putBundle(ARG_LAUNCH_OPTIONS, launchOptions)
                    putBoolean(ARG_FABRIC_ENABLED, fabricEnabled ?: false)
                }
            return CustomReactFragment().apply { setArguments(args) }
        }
    }
}