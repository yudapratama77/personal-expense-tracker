package com.yuda.personalexpensetracker.presentation.addexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuda.personalexpensetracker.util.DateFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    onBack: () -> Unit,
    viewModel: AddExpenseViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Expense")
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Track your spending easily",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Column {
                        Text(
                            text = "Title",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.title,
                            onValueChange = viewModel::onTitleChange,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("Ex: Makan Siang")
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            isError = uiState.titleError != null,
                            supportingText = {
                                uiState.titleError?.let {
                                    Text(it)
                                }
                            }
                        )
                    }

                    Column {
                        Text(
                            text = "Amount",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.amount,
                            onValueChange = viewModel::onAmountChange,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("Ex: 25000")
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            isError = uiState.amountError != null,
                            supportingText = {
                                uiState.amountError?.let {
                                    Text(it)
                                }
                            }
                        )
                    }

                    Column {
                        Text(
                            text = "Date",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) {
                                    showDatePicker = true
                                },

                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            border = CardDefaults.outlinedCardBorder()
                        ) {

                            androidx.compose.foundation.layout.Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 18.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = DateFormatter.format(uiState.date),
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.saveExpense(
                        onSuccess = onBack
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(18.dp)
            ) {

                Text(
                    text = "Save Expense",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    if (showDatePicker) {

        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {
                TextButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let {
                            viewModel.onDateChange(it)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }

        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}