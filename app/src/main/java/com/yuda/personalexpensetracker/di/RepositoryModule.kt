package com.yuda.personalexpensetracker.di

import com.yuda.personalexpensetracker.data.repository.ExpenseRepositoryImpl
import com.yuda.personalexpensetracker.domain.repository.ExpenseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExpenseRepository(
        repositoryImpl: ExpenseRepositoryImpl
    ): ExpenseRepository
}