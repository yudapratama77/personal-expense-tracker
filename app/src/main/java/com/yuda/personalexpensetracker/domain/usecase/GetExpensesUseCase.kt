package com.yuda.personalexpensetracker.domain.usecase

import com.yuda.personalexpensetracker.domain.repository.ExpenseRepository
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    operator fun invoke() = repository.getExpenses()
}