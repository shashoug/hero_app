package shashoug.com.tracker_presentation.components

import android.icu.util.LocaleData
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(date: LocalDate) : String{
    val today = LocalDate.now()
    return when(date) {
        today -> "Today"
        today.minusDays(1) -> "Yesterday"
        today.plusDays(1) -> "Tomorrow"
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)

    }
}