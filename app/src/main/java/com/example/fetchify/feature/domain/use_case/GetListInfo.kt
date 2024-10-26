package com.example.fetchify.feature.domain.use_case

import com.example.fetchify.core.util.Resource
import com.example.fetchify.feature.domain.model.ListInfo
import com.example.fetchify.feature.domain.repository.ListInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class GetListInfo(
    private val repository: ListInfoRepository
) {
    operator fun invoke(listId: Int): Flow<Resource<List<ListInfo>>> {
        // Return an empty flow if the listId is invalid
        if (listId <= 0) {
            return emptyFlow()
        }

        // Delegate the call to the repository
        return repository.getListInfo(listId)
    }
}