import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class Analyzer(private val apps: List<GoogleApp>) {

    fun findOldestApp():String{

        return apps[0].appName
    }

    private fun convertToDate(dateString: String): Long {
        val dateList = dateString.split(" ")
        val day = dateList[1].toInt()
        val year = dateList[2].toInt()
        val dateNewFormat = if (day<10) "${dateList[0]}-0$day-${year}" else "${dateList[0]}-$day-${year}"

        val formatter = DateTimeFormatter.ofPattern("MMMM-dd-uuuu", Locale.ENGLISH)
        val date = LocalDate.parse(dateNewFormat, formatter)

        val millisecond: Long = date.atStartOfDay(ZoneOffset.MIN).toInstant().toEpochMilli()
        return millisecond
    }
}