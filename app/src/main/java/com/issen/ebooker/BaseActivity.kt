package com.issen.ebooker

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    //todo quick solution, to refactor, probably using dependency injection and interceptor
    companion object{
        var token = ""
    }
}


