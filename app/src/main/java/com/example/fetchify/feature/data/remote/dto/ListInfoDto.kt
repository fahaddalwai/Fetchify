package com.example.fetchify.feature.data.remote.dto

import com.example.fetchify.feature.data.local.entity.ListInfoEntity
import com.example.fetchify.feature.domain.model.ListInfo

data class ListInfoDto(
    val listId: Int,
    val name: String?,
    val id: Int
) {
    fun toListInfo(): ListInfo {
        return ListInfo(
            listId = listId,
            name = name,
            id = id
        )
    }

    fun toListInfoEntity(): ListInfoEntity {
        return ListInfoEntity(
            listId = listId,
            name = name,
            id = id
        )
    }
}