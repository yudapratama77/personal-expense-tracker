package com.yuda.personalexpensetracker.domain.usecase

import com.yuda.personalexpensetracker.domain.model.Expense
import com.yuda.personalexpensetracker.domain.repository.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {

    suspend operator fun invoke(expense: Expense) {
        repository.addExpense(expense)
    }
}