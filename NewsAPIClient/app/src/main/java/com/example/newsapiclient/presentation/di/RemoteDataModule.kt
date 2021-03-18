package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.api.NewsAPIService
import com.example.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }



}