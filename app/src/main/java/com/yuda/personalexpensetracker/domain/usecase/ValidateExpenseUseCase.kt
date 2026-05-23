package com.yuda.personalexpensetracker.domain.usecase

import javax.inject.Inject

class ValidateExpenseUseCase @Inject constructor() {

    fun validateTitle(title: String): String? {
        return when {
            title.isBlank() -> "Title cannot be empty"
            else -> null
        }
    }

    fun validateAmount(amount: String): String? {
        return when {
            amount.isBlank() -> "Amount cannot be empty"
            amount.toDoubleOrNull() == null -> "Invalid amount"
            amount.toDouble() <= 0 -> "Amount must be greater than 0"
            else -> null
        }
    }
}