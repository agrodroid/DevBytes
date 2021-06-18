package com.example.devbytesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.devbytesapp.models.DatabaseVideo

@Dao
interface DatabaseVideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(listOfVideos: List<DatabaseVideo>)

    @Query("SELECT * FROM databasevideotable")
    fun getVideosFromDb(): LiveData<List<DatabaseVideo>>
}