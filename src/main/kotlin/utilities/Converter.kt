package utilities
import java.text.SimpleDateFormat
import java.util.*

fun convertStringToDate (dateString:String): Date{
    val dateList = dateString.split(" ")
    val dateNewFormat =
        if (dateList[1].toInt() < 10) "${dateList[0]}-0${dateList[1]}-${dateList[2]}"
        else "${dateList[0]}-${dateList[1]}-${dateList[2]}"
    return SimpleDateFormat(Constant.DATE_FORMAT).parse(dateNewFormat)
}

fun calculatePercentage(dividend:Int, divisor:Int):Double =
    if (divisor!=0){
        String.format("%.1f", 100.0 * dividend.div(divisor.toDouble())).toDouble()
    } else -1.0

fun convertToDouble(version:String):Double? = version.split(" ").first().toDoubleOrNull()

fun convertToNumber(size:String): Long {
    when {
        size.endsWith("k", true)->{
            return size.substring(0,size.indexOf("k")).toLong()
        }
        size.endsWith("M", true)->{
            return (size.substring(0,size.indexOf("M")).toDouble()* 1000).toLong()
        }
        size.endsWith("G", true)->{
            return (size.substring(0,size.indexOf("G")).toDouble()* 1000000).toLong()
        }
    }
    return 0
}