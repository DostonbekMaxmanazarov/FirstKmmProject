package com.example.weatherappkmm

import platform.Foundation.NSBundle

actual class Platform actual constructor() {
    actual val platformName: String = getPlatform()
    actual val appVersionCode: String = getAppVersion()
    actual val appLanguage: String = getLanguage()
    actual val token: String? = getUserToken()


    private fun getPlatform() = "IOS"

    private fun getAppVersion() =
        NSBundle.mainBundle().infoDictionary?.get("CFBundleShortVersionString") as String

    private fun getLanguage() = "en"

    private fun getUserToken() = null
}