package com.minaMikhail.home.di

import com.minaMikhail.home.data.dataSource.local.HomeLocalDataSource
import com.minaMikhail.home.data.dataSource.local.HomeLocalDataSourceImpl
import com.minaMikhail.home.data.dataSource.remote.HomeRemoteDataSource
import com.minaMikhail.home.domain.repository.HomeRepository
import com.minaMikhail.home.data.repository.HomeDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidingHomeModule {

    @Provides
    @Singleton
    fun provideApiServices(
        retrofit: Retrofit
    ): HomeRemoteDataSource = retrofit.create(HomeRemoteDataSource::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface BindingHomeModule {

    @Binds
    @Singleton
    fun bindRepository(
        homeDataRepository: HomeDataRepository
    ): HomeRepository

    @Binds
    @Singleton
    fun bindLocalDataSource(
        homeLocalDataSourceImpl: HomeLocalDataSourceImpl
    ): HomeLocalDataSource
}