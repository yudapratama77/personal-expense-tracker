package com.yuda.personalexpensetracker.presentation.expenselist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuda.personalexpensetracker.domain.usecase.GetExpensesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseListUiState())
    val uiState: StateFlow<ExpenseListUiState> = _uiState.asStateFlow()

    init {
        getExpenses()
    }

    private fun getExpenses() {
        getExpensesUseCase()
            .onEach { expenses ->
                _uiState.value = _uiState.value.copy(
                    expenses = expenses,
                    totalExpense = expenses.sumOf { it.amount }
                )
            }
            .launchIn(viewModelScope)
    }
}