package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.text.SimpleDateFormat



internal class ConverterKtTest {

    private val converter = Converter()
    @Test // Test point #3
    fun should_ReturnMinus1_When_CalculatePercentage_DivideOnZero() {
        val dividend = 10
        val divisor = 0
        val percentageResult = converter.calculatePercentage(dividend,divisor)
        assertEquals(-1.0,percentageResult)
    }

    @Test // Test point #3
    fun should_ReturnCorrectPercentage_When_CalculatePercentage() {
        val dividend = 1
        val divisor = 4
        val percentageResult = converter.calculatePercentage(dividend,divisor)
        assertEquals(25.0,percentageResult)
    }

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_hasValidDate() {
        val dateStringFormat = "May 15 2022"
        val dateInDateFormat = converter.convertStringToDate(dateStringFormat)
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-15-2022"), dateInDateFormat)
    }

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_DayLessThen10() {
        //Giving date with format day not 0X if the day less than 10
        val dateStringFormat = "May 5 2022"
        val dateInDateFormat = converter.convertStringToDate(dateStringFormat)
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-05-2022"), dateInDateFormat)
    }

    @Test // Test point #3
    fun should_ThrowException_When_WrongDateFormat() {
        //Giving date with wrong format
        val dateStringFormat = "5 5 2022"
        val wrongFormatException = Executable { converter.convertStringToDate(dateStringFormat) }
        assertThrows(Exception::class.java, wrongFormatException)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_Convert_NotValid_Version_ToDouble() {
        val version = "Varies with device"
        val versionInDoubleFormat = converter.convertToDouble(version)
        assertNull(versionInDoubleFormat)
    }

    @Test // Test point #3
    fun should_ReturnVersion_When_Convert_ValidVersion_ToDouble() {
        val version = "4.4 and up"
        val versionInDoubleFormat = converter.convertToDouble(version)
        assertEquals(4.4,versionInDoubleFormat)
    }

    @Test
    fun should_ReturnByteNumber_When_GivingValidStringSize(){
        val size = "1.5M"
        val appSizeInByte = converter.convertToByte(size)
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,appSizeInByte)
    }

    @Test
    fun should_ReturnByteNumber_When_GivingLowerCaseStringSize(){
        val size = "1.5m"
        val appSizeInByte = converter.convertToByte(size)
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,appSizeInByte)
    }

    @Test
    fun should_ReturnNull_When_SizeWithoutUnit(){
        val size = "1.5"
        val appSizeInByte = converter.convertToByte(size)
        assertNull(appSizeInByte)
    }
}