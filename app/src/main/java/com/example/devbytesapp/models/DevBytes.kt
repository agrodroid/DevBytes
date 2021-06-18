package com.example.devbytesapp.models

data class DevBytes(
        val title: String,
        val description: String,
        val url: String,
        val updated: String,
        val thumbnail: String
)

fun NetworkVideoContainer.toDevBytes(): List<DevBytes>{
    return videos.map {
        DevBytes(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail

        )
    }
}
fun List<DevBytes>.toDatabaseVideo(): List<DatabaseVideo>{
    return map {
        DatabaseVideo(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail
        )
    }
}