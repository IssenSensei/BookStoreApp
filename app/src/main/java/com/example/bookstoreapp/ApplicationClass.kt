package com.example.bookstoreapp

import android.app.Application
import io.multimoon.colorful.Defaults
import io.multimoon.colorful.ThemeColor
import io.multimoon.colorful.initColorful

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        val defaults = Defaults(
            primaryColor = ThemeColor.PURPLE,
            accentColor = ThemeColor.BLUE,
            useDarkTheme = false,
            translucent = false)
        initColorful(this, defaults)
    }
}