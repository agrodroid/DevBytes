package com.example.devbytesapp.models

data class NetworkVideoContainer(
    val videos: List<NetworkVideo>
)

data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
)