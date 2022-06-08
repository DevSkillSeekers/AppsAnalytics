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


    fun convertToDouble(version:String):Double? = version.split(" ").first().toDoubleOrNull()

    fun convertToByte(size:String): BigDecimal?{
        var result = ""
        for (character in size){
            result += if (character.isDigit()) {
                character
            }
            else if ( character == '.' && !result.contains(".")) {
                character
            }
            else if ( character == '.' && result.contains(".")) {
                return null
            }
            else {
                break
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
