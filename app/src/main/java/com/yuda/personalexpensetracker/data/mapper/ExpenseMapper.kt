package com.yuda.personalexpensetracker.data.mapper

import com.yuda.personalexpensetracker.data.local.entity.ExpenseEntity
import com.yuda.personalexpensetracker.domain.model.Expense

fun ExpenseEntity.toDomain(): Expense {
    return Expense(
        id = id,
        title = title,
        amount = amount,
        date = date
    )
}

fun Expense.toEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = id,
        title = title,
        amount = amount,
        date = date
    )
}