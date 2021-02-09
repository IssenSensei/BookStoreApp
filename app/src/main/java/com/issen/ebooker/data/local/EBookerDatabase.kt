package com.issen.ebooker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.issen.ebooker.data.local.dao.BookDao
import com.issen.ebooker.data.local.dao.EpubDao
import com.issen.ebooker.data.local.dao.ImageLinksDao
import com.issen.ebooker.data.local.dao.PdfDao
import com.issen.ebooker.data.local.models.DatabaseBookItem
import com.issen.ebooker.data.local.models.DatabaseEpub
import com.issen.ebooker.data.local.models.DatabaseImageLinks
import com.issen.ebooker.data.local.models.DatabasePdf

@Database(entities = [DatabaseBookItem::class, DatabasePdf::class, DatabaseEpub::class, DatabaseImageLinks::class], version = 1)
@TypeConverters(com.issen.ebooker.data.local.TypeConverters::class)
abstract class EBookerDatabase : RoomDatabase() {
    abstract val bookDao: BookDao
    abstract val pdfDao: PdfDao
    abstract val epubDao: EpubDao
    abstract val imageLinksDao: ImageLinksDao

    companion object {
        private var INSTANCE: EBookerDatabase? = null

        fun getDatabase(context: Context): EBookerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EBookerDatabase::class.java,
                    "ebooker_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


