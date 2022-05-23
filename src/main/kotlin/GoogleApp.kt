import java.math.BigInteger
import java.time.LocalDate
import java.time.Month
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class GoogleApp (
    val appName:String,
    val company:String,
    val category:String,
    val updatedDate:String,
    val size:String,
    val installs:BigInteger,
    val currentVersion:String,
    val requiresAndroid:String
){
    var updatedDateLong:Long = 0

    init{
        updatedDateLong = convertToDate(updatedDate)
    }

    private fun convertToDate(dateString: String): Long {
        val dateList = dateString.split(" ")
        val day = dateList[1].toInt()
        val year = dateList[2].toInt()
        val dateNewFormat = if (day<10) "${dateList[0]}-0$day-${year}" else "${dateList[0]}-$day-${year}"

        val formatter = DateTimeFormatter.ofPattern("MMMM-dd-uuuu", Locale.ENGLISH)
        val date = LocalDate.parse(dateNewFormat, formatter)

        val millisecond: Long = date.atStartOfDay(ZoneOffset.MIN).toInstant().toEpochMilli()
        println(millisecond)
        return millisecond
    }

}