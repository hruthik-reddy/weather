package global.x.weather.infrastructure.util

import java.text.SimpleDateFormat
import java.util.Locale

class DateUtil {
    companion object {
        fun getFormattedDateMonth(date: String): String {
            val dateParser = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val formatter = SimpleDateFormat("MMM d", Locale.ENGLISH)

            return formatter.format(dateParser.parse(date) ?: "")
        }
        fun getFormattedDateMonthShort(date: String): String {
            val dateParser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            val formatter = SimpleDateFormat("MMM d", Locale.ENGLISH)

            return formatter.format(dateParser.parse(date) ?: "")
        }

        fun getFormattedDateTime(date: String): String {
            val dateParser = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            return formatter.format(dateParser.parse(date) ?: "")
        }
    }

}