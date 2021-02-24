package com.issen.ebooker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.issen.ebooker.data.local.dao.*
import com.issen.ebooker.data.local.models.*

@Database(
    entities = [
        DatabaseBookItem::class,
        DatabaseImageLinks::class,
        DatabaseShelf::class,
        DatabaseUserBookItem::class],
    version = 1
)
@TypeConverters(com.issen.ebooker.data.local.TypeConverters::class)
abstract class EBookerDatabase : RoomDatabase() {
    abstract val bookDao: BookDao
    abstract val imageLinksDao: ImageLinksDao
    abstract val shelfDao: ShelfDao
    abstract val userBookDao: UserBookDao

    companion object {
        private var INSTANCE: EBookerDatabase? = null

        fun getDatabase(context: Context): EBookerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EBookerDatabase::class.java,
                    "ebooker_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


