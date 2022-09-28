package com.example.weatherappkmm

actual class Platform actual constructor() {
    actual val platformName = getPlatform()
    actual val appVersionCode = getAppVersion()
    actual val appLanguage: String = getLanguage()
    actual val token: String? = getUserToken()


    private fun getPlatform() = "ANDROID"

    private fun getAppVersion() = "1.0.1"

    private fun getLanguage() = "en"

    private fun getUserToken() = null


}