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
                        "21M",
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
                        "21M",
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
                        "21M", 500,
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
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpace(){
        //Given list of apps
        initializeAppList(1)
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google ")
        //then check the result
        assertEquals(2, result)

    }

    @Test
    fun should_Retur0_When_CompanyNameNotFound(){
        //Given list of apps
        initializeAppList(1)
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"sfkjnc")
        //then check the result
        assertEquals(0, result)

    }
    @Test
    fun should_ReturnMinus1_When_CompanyNameEmpty(){
        //Given list of apps
        initializeAppList(1)
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"")
        //then check the result
        assertEquals(-1, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameInUpperCase(){
        //Given list of apps
        initializeAppList(1)
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnMinus1_When_ListEmpty(){
        //Given list of apps
        initializeAppList(0)
        //when calculate % of medical apps in list
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        //then check the result
        assertEquals(-1, result)

    }


}

