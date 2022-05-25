import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import parser.AppParser
import utilities.Constant
import utilities.convertStringToDate
import java.text.SimpleDateFormat

internal class AnalyzerTest{

    @Test
    fun should_ReturnMinus1_When_EmptyList(){
        //Given emptyList
        val app = listOf<App>()
        //when search for the oldest app
        val result = Analyzer().findOldestApp(app)
        //then check the result
        assertEquals("-1",result)
    }

    @Test
    fun should_Return_oldestApp_whenHasValidList(){
        //Given emptyList
        val app = AppParser().parseFile(Constant.TEST_FILE_NAME)
        //when search for the oldest app
        val result = Analyzer().findOldestApp(app)
        //then check the result
        assertEquals("The Kremer Collection VR Museum",result)
    }

    @Test
    fun should_Return_ConvertedDate_When_hasValidDate(){
        //Given date with correct format MMM DD YYYY
        val date = "May 15 2022"
        //when convert the stringDate to Date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-15-2022"),result)
    }


    @Test
    fun should_Return_ConvertedDate_When_DayLessThen10(){
        //Given date with different format day is not 05
        val date = "May 5 2022"
        //when convert StringDate to date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-05-2022"),result)
    }

    @Test
    fun should_ThrowException_When_WrongDateFormat(){
        //Given date with different format
        val date = "5 5 2022"
        //when convert StringDate to date
        val wrongFormatException:Executable =Executable{ convertStringToDate(date)}
        //then check the result
        assertThrows(Exception::class.java,wrongFormatException)
    }

}