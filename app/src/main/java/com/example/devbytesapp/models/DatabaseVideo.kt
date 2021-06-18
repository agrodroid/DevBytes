package com.example.devbytesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "databasevideotable")
data class DatabaseVideo(

        @PrimaryKey
        val url: String,
        val title: String,
        val description: String,
        val updated: String,
        val thumbnail: String

){
        val shortDescription: String
                get() = description.substring(startIndex = 0, endIndex = 200) + "..."
        val updatedDate: String
                get() = updated.substring(0, 10)
}