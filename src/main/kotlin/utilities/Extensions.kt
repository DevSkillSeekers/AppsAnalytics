package utilities

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.pow

fun String.convertToByte(): BigDecimal?{
    var result = ""
    for (c in this){
        result += if (c.isDigit())
            c
        else if ( c == '.' && !result.contains(".")) {
            c
        } else {
            if ( c == '.' && result.contains("."))
                return null
            else {
                break
            }
        }
    }
    val value = result.toDoubleOrNull()
    if (value!= null) {
        return when(this[this.lastIndex]) {
            'K','k' ->  (value*Constant.KILO_BYTE).toBigDecimal()
            'M','m' -> (value*Constant.KILO_BYTE.pow(2)).toBigDecimal()
            'G','g' -> (value*Constant.KILO_BYTE.pow(3)).toBigDecimal()
            else -> null
        }
    }
    return null
}

/**
 * @param this is a input date of string
 * @return a date after convert from string
 * */
fun String.convertToDate(): Date{
    return  SimpleDateFormat(Constant.DATE_FORMAT).parse(this)
}

