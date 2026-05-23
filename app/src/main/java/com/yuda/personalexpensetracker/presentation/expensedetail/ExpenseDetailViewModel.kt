package com.yuda.personalexpensetracker.presentation.expensedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuda.personalexpensetracker.domain.model.Expense
import com.yuda.personalexpensetracker.domain.usecase.GetExpenseDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getExpenseDetailUseCase: GetExpenseDetailUseCase
) : ViewModel() {

    private val _expense = MutableStateFlow<Expense?>(null)
    val expense: StateFlow<Expense?> = _expense

    init {

        val expenseId =
            savedStateHandle.get<String>("expenseId")?.toIntOrNull()

        expenseId?.let {
            getExpense(it)
        }
    }

    private fun getExpense(id: Int) {

        viewModelScope.launch {

            _expense.value =
                getExpenseDetailUseCase(id)
        }
    }
}