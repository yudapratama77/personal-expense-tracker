package com.yuda.personalexpensetracker.di

import android.content.Context
import androidx.room.Room
import com.yuda.personalexpensetracker.data.local.dao.ExpenseDao
import com.yuda.personalexpensetracker.data.local.database.ExpenseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ExpenseDatabase {

        return Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "expense_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(
        database: ExpenseDatabase
    ): ExpenseDao {
        return database.expenseDao()
    }
}