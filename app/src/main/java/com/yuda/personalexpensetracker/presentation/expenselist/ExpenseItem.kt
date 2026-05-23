package com.yuda.personalexpensetracker.presentation.expenselist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuda.personalexpensetracker.domain.model.Expense

@Composable
fun ExpenseItem(
    expense: Expense
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = expense.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "$${expense.amount}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}