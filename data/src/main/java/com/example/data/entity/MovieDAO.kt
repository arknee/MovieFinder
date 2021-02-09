package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDAO(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val posterUrl: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String
)