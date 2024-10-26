package com.example.fetchify.feature.di

import android.app.Application
import androidx.room.Room
import com.example.fetchify.feature.data.local.ListInfoDatabase
import com.example.fetchify.feature.data.local.Converters
import com.example.fetchify.feature.data.remote.ListInfoApi
import com.example.fetchify.feature.data.remote.ListInfoApi.Companion.BASE_URL
import com.example.fetchify.feature.data.repository.ListInfoRepositoryImpl
import com.example.fetchify.feature.data.util.GsonParser
import com.example.fetchify.feature.domain.repository.ListInfoRepository
import com.example.fetchify.feature.domain.use_case.GetListInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListInfoModule {

    @Provides
    @Singleton
    fun provideGetListInfoUseCase(repository: ListInfoRepository): GetListInfo {
        return GetListInfo(repository)
    }

    @Provides
    @Singleton
    fun provideListInfoRepository(
        db: ListInfoDatabase,
        api: ListInfoApi
    ): ListInfoRepository {
        return ListInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideListInfoDatabase(app: Application): ListInfoDatabase {
        return Room.databaseBuilder(
            app, ListInfoDatabase::class.java, "listDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideFetchifyApi(): ListInfoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListInfoApi::class.java)
    }
}