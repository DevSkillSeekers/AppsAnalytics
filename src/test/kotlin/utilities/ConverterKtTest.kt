package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.text.SimpleDateFormat

internal class ConverterKtTest{

    private val converter = Converter()
    @Test // Test point #3
    fun should_ReturnMinus1_When_CalculatePercentage_DivideOnZero() {
        //Given two number
        val dividend = 10
        val divisor = 0
        //when calculate percentage and divide by zero
        val result = converter.calculatePercentage(dividend,divisor)
        //then check the result
        assertEquals(-1.0,result)
    }

    @Test // Test point #3
    fun should_ReturnCorrectPercentage_When_CalculatePercentage() {
        //Given two number
        val dividend = 1
        val divisor = 4
        //when calculate percentage and divide by zero
        val result = converter.calculatePercentage(dividend,divisor)
        //then check the result
        assertEquals(25.0,result)
    }

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_hasValidDate() {
        //Given date with correct format MMM DD YYYY
        val date = "May 15 2022"
        //when convert the stringDate to Date
        val result = converter.convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-15-2022"), result)
    }

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_DayLessThen10() {
        //Given date with different format day is not 05
        val date = "May 5 2022"
        //when convert StringDate to date
        val result = converter.convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-05-2022"), result)
    }

    @Test // Test point #3
    fun should_ThrowException_When_WrongDateFormat() {
        //Given date with different format
        val date = "5 5 2022"
        //when convert StringDate to date
        val wrongFormatException = Executable { converter.convertStringToDate(date) }
        //then check the result
        assertThrows(Exception::class.java, wrongFormatException)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_Convert_NotValid_Version_ToDouble() {
        //Given string
        val version = "Varies with device"
        //when convert String version to double
        val result = converter.convertToDouble(version)
        //then check the result
        assertNull(result)
    }

    @Test // Test point #3
    fun should_ReturnVersion_When_Convert_ValidVersion_ToDouble() {
        //Given string
        val version = "4.4 and up"
        //when convert String version to double
        val result = converter.convertToDouble(version)
        //then check the result
        assertEquals(4.4,result)
    }

    @Test
    fun should_ReturnByteNumber_When_GiveValidStringSize(){
        //Given size
        val size = "1.5M"
        //when convert app size to byte
        val result = converter.convertToByte(size)
        //then check the result
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,result)
    }

    @Test
    fun should_ReturnByteNumber_When_GiveLowerCaseStringSize(){
        //Given size
        val size = "1.5m"
        //when convert app size to byte
        val result = converter.convertToByte(size)
        //then check the result
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,result)
    }

    @Test
    fun should_ReturnNull_When_SizeNotContainUnit(){
        //Given valid size without unit
        val size = "1.5"
        //when convert app size to byte
        val result = converter.convertToByte(size)
        //then check the result
        assertNull(result)
    }
}