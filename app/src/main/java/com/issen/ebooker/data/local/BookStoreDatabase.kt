package com.issen.ebooker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.issen.ebooker.data.local.dao.BookDao
import com.issen.ebooker.data.local.models.DatabaseBookItem
import com.issen.ebooker.data.local.models.DatabaseEpub
import com.issen.ebooker.data.local.models.DatabaseImageLinks
import com.issen.ebooker.data.local.models.DatabasePdf

@Database(entities = [DatabaseBookItem::class, DatabasePdf::class, DatabaseEpub::class, DatabaseImageLinks::class], version = 1)
@TypeConverters(com.issen.ebooker.data.local.TypeConverters::class)
abstract class BookStoreDatabase : RoomDatabase() {
    abstract val bookDao: BookDao
}

private lateinit var INSTANCE: BookStoreDatabase

fun getDatabase(context: Context): BookStoreDatabase {
    synchronized(BookStoreDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BookStoreDatabase::class.java,
                "bookstore"
            ).build()
        }
    }
    return INSTANCE
}