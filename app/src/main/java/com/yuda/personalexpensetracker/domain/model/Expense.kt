package com.yuda.personalexpensetracker.domain.model

data class Expense(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val date: Long
)