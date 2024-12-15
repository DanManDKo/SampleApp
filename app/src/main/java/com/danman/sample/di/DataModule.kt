package com.danman.sample.di

import com.danman.data.repo.TopicsRepoImpl
import com.danman.domain.repo.TopicsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTopicsRepo(): TopicsRepo = TopicsRepoImpl()
}