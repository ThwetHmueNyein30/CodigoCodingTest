package com.codigo.codetest.account_create

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

val sdf = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
val sdfForServer = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

fun openDateChooser(
    context: Context,
    currentChooseDate: String?,
    selectedDate:(Date) -> Unit = {}
): DatePickerDialog {
    val startDateChooser = Calendar.getInstance()
    val maxCal = Calendar.getInstance()
    val minCal = Calendar.getInstance()
    minCal.add(Calendar.YEAR, -100)
    maxCal.add(Calendar.YEAR, -5)
    val selectedStartDate = currentChooseDate?.split("-")
    val dialog = DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->
            startDateChooser.set(Calendar.YEAR, year)
            startDateChooser.set(Calendar.MONTH, monthOfYear)
            startDateChooser.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            selectedDate(startDateChooser.time)
        },
        selectedStartDate?.get(0)?.toInt() ?: startDateChooser.get(Calendar.YEAR),
        selectedStartDate?.get(1)?.toInt()?.minus(1) ?: startDateChooser.get(Calendar.MONTH),
        selectedStartDate?.get(2)?.toInt() ?: startDateChooser.get(Calendar.DAY_OF_MONTH)
    )
    dialog.datePicker.minDate = minCal.time.time
    dialog.datePicker.maxDate = maxCal.timeInMillis
    dialog.show()
    dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).text = "Cancel"
    dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).text = "Ok"
    return dialog
}