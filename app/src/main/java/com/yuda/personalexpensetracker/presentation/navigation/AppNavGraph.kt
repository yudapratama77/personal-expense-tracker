package com.yuda.personalexpensetracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuda.personalexpensetracker.presentation.addexpense.AddExpenseScreen
import com.yuda.personalexpensetracker.presentation.expensedetail.ExpenseDetailScreen
import com.yuda.personalexpensetracker.presentation.expenselist.ExpenseListScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ExpenseList.route
    ) {

        composable(Screen.ExpenseList.route) {
            ExpenseListScreen(
                navController = navController,

                onAddExpenseClick = {
                    navController.navigate(
                        Screen.AddExpense.route
                    )
                },

                onExpenseClick = { expenseId ->
                    navController.navigate(
                        Screen.ExpenseDetail.createRoute(expenseId)
                    )
                }
            )
        }

        composable(Screen.AddExpense.route) {
            AddExpenseScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.ExpenseDetail.route) {
            ExpenseDetailScreen()
        }
    }
}