package com.yuda.personalexpensetracker.presentation.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuda.personalexpensetracker.domain.model.Expense
import com.yuda.personalexpensetracker.domain.usecase.AddExpenseUseCase
import com.yuda.personalexpensetracker.domain.usecase.ValidateExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val validateExpenseUseCase: ValidateExpenseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddExpenseUiState())
    val uiState: StateFlow<AddExpenseUiState> = _uiState.asStateFlow()

    fun onTitleChange(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onAmountChange(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
    }

    fun onDateChange(date: Long) {
        _uiState.value = _uiState.value.copy(
            date = date
        )
    }

    fun saveExpense(
        onSuccess: () -> Unit
    ) {
        val titleError = validateExpenseUseCase
            .validateTitle(_uiState.value.title)

        val amountError = validateExpenseUseCase
            .validateAmount(_uiState.value.amount)

        _uiState.value = _uiState.value.copy(
            titleError = titleError,
            amountError = amountError
        )

        if (titleError != null || amountError != null) {
            return
        }

        viewModelScope.launch {
            addExpenseUseCase(
                Expense(
                    title = _uiState.value.title,
                    amount = _uiState.value.amount.toDouble(),
                    date = _uiState.value.date
                )
            )
            onSuccess()
        }
    }
}