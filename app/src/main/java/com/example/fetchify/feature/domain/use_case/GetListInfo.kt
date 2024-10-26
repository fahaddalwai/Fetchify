package com.example.fetchify.feature.domain.use_case

import com.example.fetchify.core.util.Resource
import com.example.fetchify.feature.domain.model.ListInfo
import com.example.fetchify.feature.domain.repository.ListInfoRepository
import kotlinx.coroutines.flow.Flow


class GetListInfo(
    private val repository: ListInfoRepository
) {
    operator fun invoke(): Flow<Resource<List<ListInfo>>> {
        // Delegate the call to the repository
        return repository.getListInfo()
    }
}