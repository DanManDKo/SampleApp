package com.danman.sample.di

import com.danman.data.datastore.remote.TopicRemoteDataSource
import com.danman.data.datastore.remote.TopicsRemoteDataSourceImpl
import com.danman.data.mappers.TopicsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideTopicsRemoteDataSource(topicsMapper: TopicsMapper): TopicRemoteDataSource =
        TopicsRemoteDataSourceImpl(topicsMapper)
}