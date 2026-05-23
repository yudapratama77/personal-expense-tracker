package com.yuda.personalexpensetracker.domain.usecase

import com.yuda.personalexpensetracker.domain.repository.ExpenseRepository
import javax.inject.Inject

class GetExpenseDetailUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {

    suspend operator fun invoke(id: Int) =
        repository.getExpenseById(id)
}