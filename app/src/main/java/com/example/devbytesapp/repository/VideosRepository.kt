package com.example.devbytesapp.repository

import com.example.devbytesapp.models.DatabaseVideo
import com.example.devbytesapp.database.VideoDatabase
import com.example.devbytesapp.network.ApiService
import com.example.devbytesapp.models.toDatabaseVideo
import com.example.devbytesapp.models.toDevBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideoDatabase) {

    val videosList = database.databaseVideoDao.getVideosFromDb()

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val receivedResponse = ApiService.retrofitService.getDevbytesList().toDevBytes()
            insert(receivedResponse.toDatabaseVideo())
        }
    }
    private suspend fun insert(videos: List<DatabaseVideo>){
        database.databaseVideoDao.insertVideos(videos)
    }
}