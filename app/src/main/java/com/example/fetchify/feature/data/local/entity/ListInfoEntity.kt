package com.example.fetchify.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fetchify.feature.domain.model.ListInfo

@Entity
data class ListInfoEntity(
    @PrimaryKey val id: Int,
    val listId: Int,
    val name: String?
) {
    fun toListInfo(): ListInfo {
        return ListInfo(
            id = id,
            listId = listId,
            name = name ?: "Unnamed"  // Default to "Unnamed" if name is null or empty
        )
    }
}