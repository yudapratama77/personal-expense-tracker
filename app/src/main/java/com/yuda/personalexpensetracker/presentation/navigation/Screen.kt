package com.yuda.personalexpensetracker.presentation.navigation

sealed class Screen(val route: String) {

    data object ExpenseList : Screen("expense_list")

    data object AddExpense : Screen("add_expense")

    data object ExpenseDetail : Screen("expense_detail/{expenseId}") {
        fun createRoute(expenseId: Int): String {
            return "expense_detail/$expenseId"
        }
    }
}