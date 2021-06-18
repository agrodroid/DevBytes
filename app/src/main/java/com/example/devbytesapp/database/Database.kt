package com.example.devbytesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.devbytesapp.models.DatabaseVideo

@Database(entities = [DatabaseVideo::class], version = 2, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {

    abstract val databaseVideoDao: DatabaseVideoDao

    companion object {

        @Volatile
        private var INSTANCE: VideoDatabase? = null

        fun getInstance(context: Context): VideoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            VideoDatabase::class.java,
                            "video_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}