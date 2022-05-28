import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.AppParser
import utilities.Constant
import utilities.TestConstant
import utilities.calculatePercentage
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AnalyzerTest {

    private lateinit var analyzer: Analyzer
    private lateinit var apps: MutableList<App>

    @BeforeAll
    fun setup() {
        analyzer = Analyzer()
    }

    private fun initializeAppList(testListNumber: Int){
        when (testListNumber) {
            TestConstant.EMPTY_LIST -> apps=  mutableListOf()
            TestConstant.VALID_LIST -> {
                apps=  mutableListOf()
                apps.add(
                    App(
                        "Books",
                        "Amazon",
                        "Libraries & Demo",
                        Date("5/1/2000"),
                        "21M", 500,
                        "1.0.0",
                        4.4
                    )
                )
                apps.add(
                    App(
                        "AD",
                        "Amazon",
                        "Libraries & Demo",
                        Date("1/1/2020"),
                        "21M",
                        500,
                        "1.0.0",
                        9.0
                    )
                )
                apps.add(
                    App(
                        "Google Photo",
                        "Google",
                        "Libraries & Demo",
                        Date("2/1/2000"),
                        "21M",
                        500,
                        "1.0.0",
                        6.0
                    )
                )
                apps.add(
                    App(
                        "Google Files",
                        "Google",
                        "Medical",
                        Date("1/1/2019"),
                        "21M", 500,
                        "1.0.0",
                        9.0
                    )
                )
            }
        }
    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_ValidList() {
        //Given valid list of apps and valid categoryName
        initializeAppList(TestConstant.VALID_LIST)
        val categoryName = "Medical"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_CategoryWithUpperOrLowerCase() {
        //Given list of apps and upperCase categoryName
        initializeAppList(TestConstant.VALID_LIST)
        val categoryName = "MEDICAL"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps,categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_CategoryWithSpace() {
        //Given valid list of apps and category name with space
        initializeAppList(TestConstant.VALID_LIST)
        val categoryName = " MEDICAL "
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_EmptyList() {
        //Given empty list of apps
        initializeAppList(TestConstant.EMPTY_LIST)
        //when calculate % of medical apps in emptyList
        val result = analyzer.getPercentageOfCategory(apps, "Medical")
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnZero_When_CategoryNotFound() {
        //Given valid list of apps and category name not in list
        initializeAppList(TestConstant.VALID_LIST)
        //when calculate % of category that not in list
        val result = analyzer.getPercentageOfCategory(apps, "Shopping")
        //then check the result
        assertEquals(0.0, result)
    }


    @Test // Test Point #2
    fun should_ReturnMinus1_When_CategoryIsBlank() {
        //Given valid list of apps and empty category
        initializeAppList(TestConstant.VALID_LIST)
        //when calculate % empty category name
        val result = analyzer.getPercentageOfCategory(apps, "")
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_EmptyList() {
        //Given emptyList
        initializeAppList(TestConstant.EMPTY_LIST)
        //when search for the oldest app
        val result = analyzer.findOldestApp(apps)
        //then check the result
        assertNull(result)
    }

    @Test // Test point #3
    fun should_Return_oldestApp_whenHasValidList() {
        //Given valid list
        initializeAppList(TestConstant.VALID_LIST)
        //when search for the oldest app
        val result = analyzer.findOldestApp(apps)
        //then check the result
        assertEquals("Google Photo", result)
    }

    @Test // Test point #3
    fun should_ReturnMinus1_When_CalculatePercentage_DivideOnZero() {
        //Given two number
        val dividend =10
        val divisor =0
        //when calculate percentage and divide by zero
        val result = calculatePercentage(dividend,divisor)
        //then check the result
        assertEquals(-1.0,result)
    }

    @Test // Test point #3
    fun should_ReturnCorrectPercentage_When_CalculatePercentage() {
        //Given two number
        val dividend = 1
        val divisor = 4
        //when calculate percentage and divide by zero
        val result = calculatePercentage(dividend,divisor)
        //then check the result
        assertEquals(25.0,result)
    }

    @Test// Test point #6
    fun should_ReturnNull_When_TheListIsEmpty() {
        //given empty list
        val appList = mutableListOf<App>()

        //when search for find top ten app Install
        val result = analyzer.topTenAppInstall(appList)

        //then should return null
        kotlin.test.assertNull(result)
    }

    @Test// Test point #6
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
}