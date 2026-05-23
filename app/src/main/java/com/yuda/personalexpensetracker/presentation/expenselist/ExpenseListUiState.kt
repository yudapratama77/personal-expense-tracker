package com.yuda.personalexpensetracker.presentation.expenselist

import com.yuda.personalexpensetracker.domain.model.Expense

data class ExpenseListUiState(
    val isLoading: Boolean = false,
    val expenses: List<Expense> = emptyList(),
    val totalExpense: Double = 0.0,
    val error: String? = null
)