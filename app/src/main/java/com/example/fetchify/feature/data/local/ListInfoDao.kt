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

    @Query("""
    SELECT * FROM listinfoentity
    WHERE name IS NOT NULL AND name != ''
    ORDER BY listId, name
""")
    suspend fun getListInfos(): List<ListInfoEntity>

}