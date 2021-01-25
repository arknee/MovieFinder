package com.example.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.MovieDAO

@Database(entities = [MovieDAO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}