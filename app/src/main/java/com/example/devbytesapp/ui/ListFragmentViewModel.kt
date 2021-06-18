package com.example.devbytesapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.devbytesapp.database.VideoDatabase
import com.example.devbytesapp.repository.VideosRepository
import kotlinx.coroutines.launch


class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val videosRepository = VideosRepository(VideoDatabase.getInstance(application))

    val playlist = videosRepository.videosList

    init {
        getList()
    }

    private fun getList(){
        viewModelScope.launch {
            try {
                videosRepository.refreshVideos()
            }catch (e:Exception){
                Log.e("getListError", e.toString())
            }
        }
    }
}