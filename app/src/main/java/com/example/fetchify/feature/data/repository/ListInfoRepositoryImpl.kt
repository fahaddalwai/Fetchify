package com.example.fetchify.feature.data.repository

import com.example.fetchify.core.util.Resource
import com.example.fetchify.feature.data.local.ListInfoDao
import com.example.fetchify.feature.data.remote.ListInfoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ListInfoRepositoryImpl(
    private val api: ListInfoApi,
    private val dao: ListInfoDao
) : ListInfoRepository {

    override fun getListInfo(itemId: String): Flow<Resource<List<ListInfo>>> = flow {
        emit(Resource.Loading())

        // Fetch cached list info from the local database
        val cachedListInfo = dao.getListInfos(itemId).map { it.toListInfo() }
        emit(Resource.Loading(data = cachedListInfo))

        try {
            // Fetch fresh list info from the remote API
            val remoteListInfo = api.getListInfo(itemId)
            dao.deleteListInfos(remoteListInfo.map { it.id }) // Clear outdated items
            dao.insertListInfos(remoteListInfo.map { it.toListInfoEntity() }) // Insert new items
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong",
                data = cachedListInfo // Return cached items in case of an error
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Internet not working",
                data = cachedListInfo // Return cached items in case of an error
            ))
        }

        // Emit the updated list of items from the local database
        val newListInfo = dao.getListInfos(itemId).map { it.toListInfo() }
        emit(Resource.Success(newListInfo))
    }
}