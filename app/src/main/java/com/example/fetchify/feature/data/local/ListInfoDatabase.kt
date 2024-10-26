package com.example.dictionaryapp.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.fetchify.feature.data.local.Converters
import com.example.fetchify.feature.data.local.ListInfoDao
import com.example.fetchify.feature.data.local.entity.ListInfoEntity

@Database(
    entities = [ListInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ListInfoDatabase : RoomDatabase() {
    abstract val dao: ListInfoDao
}

