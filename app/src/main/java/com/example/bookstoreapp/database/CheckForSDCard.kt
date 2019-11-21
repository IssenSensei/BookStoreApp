package com.example.bookstoreapp.database

import android.os.Environment

 class CheckForSDCard {
 //Check If SD Card is present or not method
     val isSDCardPresent:Boolean
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}