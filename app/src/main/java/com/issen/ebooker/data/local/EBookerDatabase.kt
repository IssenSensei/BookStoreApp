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
        DatabasePdf::class,
        DatabaseEpub::class,
        DatabaseImageLinks::class,
        DatabaseShelf::class,
        DatabaseUserBookItem::class,
        DatabaseQuotationItem::class,
        DatabaseReviewItem::class],
    version = 1
)
@TypeConverters(com.issen.ebooker.data.local.TypeConverters::class)
abstract class EBookerDatabase : RoomDatabase() {
    abstract val bookDao: BookDao
    abstract val pdfDao: PdfDao
    abstract val epubDao: EpubDao
    abstract val imageLinksDao: ImageLinksDao
    abstract val shelfDao: ShelfDao
    abstract val userBookDao: UserBookDao
    abstract val quotationDao: QuotationDao
    abstract val reviewDao: ReviewDao

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


