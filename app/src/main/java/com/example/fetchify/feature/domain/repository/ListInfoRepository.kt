package com.example.fetchify.feature.domain.repository

import com.example.fetchify.core.util.Resource
import com.example.fetchify.feature.domain.model.ListInfo
import kotlinx.coroutines.flow.Flow

interface ListInfoRepository {
    fun getListInfo(listId: Int): Flow<Resource<List<ListInfo>>>
}