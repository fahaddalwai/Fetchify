package com.example.fetchify.feature.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fetchify.feature.data.local.entity.ListInfoEntity

@Dao
interface ListInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListInfos(infos: List<ListInfoEntity>)

    @Query("DELETE FROM listinfoentity")
    suspend fun deleteListInfos()

    @Query("SELECT * FROM listinfoentity")
    suspend fun getListInfos(): List<ListInfoEntity>

}