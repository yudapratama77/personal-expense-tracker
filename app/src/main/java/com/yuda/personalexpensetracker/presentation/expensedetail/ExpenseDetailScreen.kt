package com.yuda.personalexpensetracker.presentation.expensedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuda.personalexpensetracker.util.CurrencyFormatter
import com.yuda.personalexpensetracker.util.DateFormatter

@Composable
fun ExpenseDetailScreen(
    viewModel: ExpenseDetailViewModel = hiltViewModel()
) {

    val expense by viewModel.expense.collectAsState()

    Scaffold { paddingValues ->

        expense?.let {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                Text(
                    text = it.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = CurrencyFormatter.format(it.amount),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = DateFormatter.format(it.date),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}