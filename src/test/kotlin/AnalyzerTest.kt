import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.AppParser
import utilities.Constant
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
            0 -> apps=  mutableListOf()
            1-> {
                apps=  mutableListOf()
                apps.add(
                    App(
                        "Books",
                        "Amazon",
                        "Libraries & Demo",
                        Date("5/1/2000"),
                        "21M", 500,
                        "1.0.0",
                        "4.4"
                    )
                )
                apps.add(
                    App(
                        "AD",
                        "Amazon",
                        "Libraries & Demo",
                        Date("1/1/2020"),
                        "30M",
                        500,
                        "1.0.0",
                        "9.0"
                    )
                )
                apps.add(
                    App(
                        "Google Photo",
                        "Google",
                        "Libraries & Demo",
                        Date("2/1/2000"),
                        "50M",
                        500,
                        "1.0.0",
                        "6.0"
                    )
                )
                apps.add(
                    App(
                        "Google Files",
                        "Google",
                        "Medical",
                        Date("1/1/2019"),
                        "5M", 500,
                        "1.0.0",
                        "9.0"
                    )
                )
            }
        }
    }


    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameEqualsToGoogle(){
        //Given list of apps
        initializeAppList(1)
        ////when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(2, result)

    }

    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpaceAtTheEnd(){
        //Given list of apps
        initializeAppList(1)
        ////when calculate number of apps that developed by companyName contains space at the end
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google ")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpace(){
        //Given list of apps
        initializeAppList(1)
        ////when calculate number of apps that developed by companyName contains space
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"goo gle")
        //then check the result
        assertEquals(0, result)

    }
    @Test
    fun should_Retur0_When_CompanyNameNotFound(){
        //Given list of apps
        initializeAppList(1)
        ////when calculate number of apps that developed by companyName doesn't exist
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"sfkjnc")
        //then check the result
        assertEquals(0, result)

    }
    @Test
    fun should_ReturnMinus1_When_CompanyNameEmpty(){
        //Given list of apps
        initializeAppList(1)
        //when calculate number of apps that developed by companyName is empty
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"")
        //then check the result
        assertEquals(-1, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameInUpperCase(){
        //Given list of apps
        initializeAppList(1)
        //when calculate number of apps that developed by companyName in upper case
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnMinus1_When_ListEmpty(){
        //Given empty list of apps
        initializeAppList(0)
        //when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(-1, result)

    }
    @Test
    fun should_Return_Top10LargestApp() {
        //given list with data of the file & the number of value will return
        initializeAppList(1)
        var valueReturnNumber= 4
        //when search for top 10 largest app
        val functionResultValue = analyzer.getLargestApp(apps,valueReturnNumber)
        //then should return that order of top 10 largest app that compares the size with unit KB
        val expectedResultValue = listOf("Google Photo", "AD","Books", "Google Files")
        assertEquals(expectedResultValue, functionResultValue)
    }



}