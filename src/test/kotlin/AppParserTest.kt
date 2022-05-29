import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import utilities.Constant
import utilities.convertStringToDate
import utilities.convertToDouble
import java.text.SimpleDateFormat

internal class AppParserTest{

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_hasValidDate() {
        //Given date with correct format MMM DD YYYY
        val date = "May 15 2022"
        //when convert the stringDate to Date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-15-2022"), result)
    }

    @Test // Test point #3
    fun should_Return_ConvertedDate_When_DayLessThen10() {
        //Given date with different format day is not 05
        val date = "May 5 2022"
        //when convert StringDate to date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-05-2022"), result)
    }

    @Test // Test point #3
    fun should_ThrowException_When_WrongDateFormat() {
        //Given date with different format
        val date = "5 5 2022"
        //when convert StringDate to date
        val wrongFormatException = Executable { convertStringToDate(date) }
        //then check the result
        assertThrows(Exception::class.java, wrongFormatException)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_Convert_NotValid_Version_ToDouble() {
        //Given string
        val version = "Varies with device"
        //when convert StringDate to date
        val result = convertToDouble(version)
        //then check the result
        assertNull(result)
    }

    @Test // Test point #3
    fun should_ReturnVersion_When_Convert_ValidVersion_ToDouble() {
        //Given string
        val version = "4.4 and up"
        //when convert StringDate to date
        val result = convertToDouble(version)
        //then check the result
        assertEquals(4.4,result)
    }
}