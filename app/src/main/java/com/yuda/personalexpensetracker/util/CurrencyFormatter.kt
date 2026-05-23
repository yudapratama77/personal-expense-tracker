package com.yuda.personalexpensetracker.util

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {

    fun format(amount: Double): String {

        val formatter =
            NumberFormat.getCurrencyInstance(
                Locale("in", "ID")
            )

        formatter.maximumFractionDigits = 0

        return formatter.format(amount)
    }
}