package utilities

import model.App
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import java.text.SimpleDateFormat
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ConverterKtTest{
    lateinit var converter: Converter
    @BeforeAll
    fun setup() {
        converter = Converter()
    }
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

    @Test
    fun should_ReturnApp_when_ParsingSeparatedString () {
        // Given separated value string
        val separatedValue = "Books,Amazon,Libraries & Demo,April 20 2022,21M,500000,1.0.0,4.4"
        // When parsing string

        val excepted = App("Books", "Amazon", "Libraries & Demo", Date("4/20/2022"), "21M", 500000, "1.0.0", 4.4)
        val result = converter.convertStringWithCommaToApp(separatedValue)
        // then
        assertEquals(excepted, result)
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






}