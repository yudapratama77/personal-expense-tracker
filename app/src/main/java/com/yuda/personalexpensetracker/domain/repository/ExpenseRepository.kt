package com.yuda.personalexpensetracker.domain.repository

import com.yuda.personalexpensetracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun getExpenses(): Flow<List<Expense>>

    suspend fun addExpense(expense: Expense)

    suspend fun deleteExpense(expense: Expense)
}