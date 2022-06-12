package utilities
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

class Converter {

    fun convertStringToDate (dateString:String): Date{
        val dateList = dateString.split(" ")
        val dateNewFormat =
            if (dateList[1].toInt() < 10) {
                "${dateList[0]}-0${dateList[1]}-${dateList[2]}"
            }
            else {
                "${dateList[0]}-${dateList[1]}-${dateList[2]}"
            }
        return SimpleDateFormat(Constant.DATE_FORMAT).parse(dateNewFormat)
    }

    fun calculatePercentage(dividend:Int, divisor:Int):Double {
        return if (divisor!=0){
            String.format("%.1f", 100.0 * dividend.div(divisor.toDouble())).toDouble()
        } else {
            -1.0
        }
    }


    fun convertToDouble(version:String):Double? = version.subSequence(0, version.indexOf(' ')).toString().toDoubleOrNull()

    fun convertToByte(size:String): BigDecimal?{
        var result = ""
        size.forEach {
            result += if (it.isDigit()) {
                it
            }
            else if ( it == '.' && !result.contains(".")) {
                it
            }
            else if ( it == '.' && result.contains(".")) {
                return null
            }
            else {
                ""
            }
        }
        val value = result.toDoubleOrNull()
        if (value!= null) {
            return when(size[size.lastIndex]) {
                'K','k' ->  (value*Constant.KILO_BYTE).toBigDecimal()
                'M','m' -> (value*Constant.KILO_BYTE.pow(2)).toBigDecimal()
                'G','g' -> (value*Constant.KILO_BYTE.pow(3)).toBigDecimal()
                else -> null
            }
        }
        return null
    }
}
