package com.yuda.personalexpensetracker.presentation.expensedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuda.personalexpensetracker.util.CurrencyFormatter
import com.yuda.personalexpensetracker.util.DateFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDetailScreen(
    onBack: () -> Unit,
    viewModel: ExpenseDetailViewModel = hiltViewModel()
) {

    val expense by viewModel.expense.collectAsState()

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("Expense Detail")
                },

                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }

    ) { paddingValues ->

        expense?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = CurrencyFormatter.format(data.amount),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider()

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),

                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        ExpenseDetailItem(
                            title = "Date",
                            value = DateFormatter.format(data.date)
                        )

                        ExpenseDetailItem(
                            title = "Category",
                            value = when {

                                data.title.contains("bensin", true) ->
                                    "Transportation"

                                data.title.contains("makan", true) ->
                                    "Food"

                                else ->
                                    "General"
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseDetailItem(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}