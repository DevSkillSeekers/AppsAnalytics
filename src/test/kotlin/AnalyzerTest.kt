import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import parser.AppParser
import utilities.Constant
import utilities.convertStringToDate
import java.text.SimpleDateFormat

internal class AnalyzerTest{


    private lateinit var analyzer: Analyzer

    @BeforeAll
    fun setup() {
        analyzer = Analyzer()
    }

    @Test
    fun should_ReturnNull_When_TheListIsEmpty() {
        //given empty list
        val appList = mutableListOf<App>()

        //when search for find top ten app Install
        val result = analyzer.topTenAppInstall(appList)

        //then should return null
        assertNull(result)
    }

    @Test
    fun should_ReturnTopTenAppInstall_WhenHasValidData() {
        //given list have data
        val appList = AppParser().parseFile(Constant.FILE_NAME)

        //when search for top 10 app install
        val result = analyzer.topTenAppInstall(appList)

        //then should return that order of top 10 install app
        val predictable = listOf(
            "Gmail",
            "Google Chrome: Fast & Secure",
            "Google",
            "Google Maps",
            "YouTube",
            "Google Photos",
            "Google Drive",
            "Gboard - the Google Keyboard",
            "WhatsApp Messenger",
            "Google Assistant"
        )
        assertEquals(predictable, result)
    }


    @Test
    fun should_ReturnMinus1_When_EmptyList() {
        //Given emptyList
        val app = listOf<App>()
        //when search for the oldest app
        val result = Analyzer().findOldestApp(app)
        //then check the result
        assertEquals("-1", result)
    }

    @Test
    fun should_Return_oldestApp_whenHasValidList() {
        //Given emptyList
        val app = AppParser().parseFile(Constant.TEST_FILE_NAME)
        //when search for the oldest app
        val result = Analyzer().findOldestApp(app)
        //then check the result
        assertEquals("The Kremer Collection VR Museum", result)
    }

    @Test
    fun should_Return_ConvertedDate_When_hasValidDate() {
        //Given date with correct format MMM DD YYYY
        val date = "May 15 2022"
        //when convert the stringDate to Date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-15-2022"), result)
    }


    @Test
    fun should_Return_ConvertedDate_When_DayLessThen10() {
        //Given date with different format day is not 05
        val date = "May 5 2022"
        //when convert StringDate to date
        val result = convertStringToDate(date)
        //then check the result
        assertEquals(SimpleDateFormat(Constant.DATE_FORMAT).parse("MAY-05-2022"), result)
    }

    @Test
    fun should_ThrowException_When_WrongDateFormat() {
        //Given date with different format
        val date = "5 5 2022"
        //when convert StringDate to date
        val wrongFormatException = Executable { convertStringToDate(date) }
        //then check the result
        assertThrows(Exception::class.java, wrongFormatException)
    }

    @Test
    fun should_ReturnNull_When_EmptyList() {
        //given empty list
        val appList = mutableListOf<App>()

        // when searching for "9 and up"
        val result = analyzer.percentageAppsRunningOnAndroid9(appList)

        //then should return null
        assertNull(result)
    }

    @Test
    fun `should_ReturnZero_When_Don'tContains9`() {
        // given list
        val appList = App(
            "Catholic Teachings",
            "MissalDaily.com",
            "Libraries & Demo",
            convertStringToDate("November 7 2019"),
            "11M",
            1,
            "3",
            "4.4 and up"
        )

        // when searching for "9 and up"
        val result = analyzer.percentageAppsRunningOnAndroid9(listOf(appList))

        //then should return null
        assertNull(result)
    }

    @Test
    fun should_ReturnPercentage_When_Contains9() {
        // given list
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Catholic Teachings",
                "MissalDaily.com",
                "Libraries & Demo",
                convertStringToDate("November 7 2019"),
                "11M",
                1,
                "3",
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "Diamonds | Pets Rock", "TIMEFLIK (ex MR TIME)",
                "Personalization",
                convertStringToDate("August 18 2021"),
                "4.7M",
                10,
                "1.0.0",
                "9 and up"
            )
        )
        // when searching for "9 and up"
        val result = analyzer.percentageAppsRunningOnAndroid9(appList)

        //then should return 50.00
        assertEquals("50.0", result)
    }
}