package com.example.weatherappkmm

expect class Platform() {
    val platformName: String
    val appVersionCode: String
    val appLanguage: String
    val token: String?

}