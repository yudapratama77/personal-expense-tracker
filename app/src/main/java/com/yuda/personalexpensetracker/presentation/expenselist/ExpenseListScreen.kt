package com.yuda.personalexpensetracker.presentation.expenselist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yuda.personalexpensetracker.util.CurrencyFormatter
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    navController: NavController,
    onAddExpenseClick: () -> Unit,
    onExpenseClick: (Int) -> Unit,
    viewModel: ExpenseListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expense Tracker"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddExpenseClick,
                shape = CircleShape
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->

        if (uiState.expenses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No expenses yet"
                )
            }

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 100.dp
                ),

                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    ExpenseSummaryCard(
                        totalExpense = uiState.totalExpense,
                        totalTransaction = uiState.expenses.size
                    )
                }

                item {
                    Text(
                        text = "Transactions",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                items(uiState.expenses) { expense ->
                    ExpenseItem(
                        expense = expense,
                        onClick = {
                            onExpenseClick(expense.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseSummaryCard(
    totalExpense: Double,
    totalTransaction: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF7F7FD5),
                            Color(0xFF86A8E7),
                            Color(0xFF91EAE4)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(
                    text = "Total Expense",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = CurrencyFormatter.format(totalExpense),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$totalTransaction Transactions",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}