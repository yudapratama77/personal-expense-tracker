package com.yuda.personalexpensetracker.presentation.addexpense

data class AddExpenseUiState(
    val title: String = "",
    val amount: String = "",
    val date: Long = System.currentTimeMillis(),
    val titleError: String? = null,
    val amountError: String? = null
)