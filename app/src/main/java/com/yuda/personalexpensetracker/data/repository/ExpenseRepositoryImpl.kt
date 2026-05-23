package com.yuda.personalexpensetracker.data.repository

import com.yuda.personalexpensetracker.data.local.dao.ExpenseDao
import com.yuda.personalexpensetracker.data.mapper.toDomain
import com.yuda.personalexpensetracker.data.mapper.toEntity
import com.yuda.personalexpensetracker.domain.model.Expense
import com.yuda.personalexpensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {

    override fun getExpenses(): Flow<List<Expense>> {
        return dao.getExpenses().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addExpense(expense: Expense) {
        dao.insertExpense(expense.toEntity())
    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(expense.toEntity())
    }
}