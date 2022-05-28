import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
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

    @BeforeEach
    fun initialize() {
        apps=  mutableListOf()
    }

    private fun setList():MutableList<App>{
        val  appList=  mutableListOf<App>()
        appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M", 500, "1.0.0", 4.4))
        appList.add( App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "21M", 500, "1.0.0", 9.0))
        appList.add(App("Google Photo", "Google", "Libraries & Demo", Date("2/1/2000"), "21M", 500, "1.0.0", 6.0))
        appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "21M", 500, "1.0.0", 9.0))
        return appList
    }

    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameEqualsToGoogle(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(2, result)

    }

    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpaceAtTheEnd(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName contains space at the end
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google ")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpace(){
        //Given list of apps
        apps
        ////when calculate number of apps that developed by companyName contains space
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"goo gle")
        //then check the result
        assertEquals(0, result)

    }
    @Test
    fun should_ReturnZero_When_CompanyNameNotFound(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName doesn't exist
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"sfkjnc")
        //then check the result
        assertEquals(0, result)

    }
    @Test
    fun should_ReturnMinus1_When_CompanyNameEmpty(){
        //Given list of apps
        apps = setList()
        //when calculate number of apps that developed by companyName is empty
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"")
        //then check the result
        assertEquals(-1, result)

    }
    @Test
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameInUpperCase(){
        //Given list of apps
        apps = setList()
        //when calculate number of apps that developed by companyName in upper case
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        //then check the result
        assertEquals(2, result)

    }
    @Test
    fun should_ReturnMinus1_When_ListEmpty(){
        //Given empty list of apps
        app
        //when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(-1, result)

    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_ValidList() {
        //Given valid list of apps and valid categoryName
        apps = setList()
        val categoryName = "Medical"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_CategoryWithUpperOrLowerCase() {
        //Given list of apps and upperCase categoryName
        apps = setList()
        val categoryName = "MEDICAL"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps,categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentage_When_CategoryWithSpace() {
        //Given valid list of apps and category name with space
        apps = setList()
        val categoryName = " MEDICAL "
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_EmptyList() {
        //Given empty list of apps
        apps
        //when calculate % of medical apps in emptyList
        val result = analyzer.getPercentageOfCategory(apps, "Medical")
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnZero_When_CategoryNotFound() {
        //Given valid list of apps and category name not in list
        apps = setList()
        //when calculate % of category that not in list
        val result = analyzer.getPercentageOfCategory(apps, "Shopping")
        //then check the result
        assertEquals(0.0, result)
    }


    @Test // Test Point #2
    fun should_ReturnMinus1_When_CategoryIsBlank() {
        //Given valid list of apps and empty category
        apps = setList()
        //when calculate % empty category name
        val result = analyzer.getPercentageOfCategory(apps, "")
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_EmptyList() {
        //Given emptyList
        apps
        //when search for the oldest app
        val result = analyzer.findOldestApp(apps)
        //then check the result
        assertNull(result)
    }

    @Test // Test point #3
    fun should_Return_oldestApp_whenHasValidList() {
        //Given valid list
        apps = setList()
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
        //Given empty list
        apps
        //when search for find top ten app Install
        val result = analyzer.topTenAppInstall(apps = apps, size = 10)
        //then should return null
        assertNull(result)
    }

    @Test// Test point #6
    fun should_ReturnTopAppInstall_WhenHasValidData() {
        //Given list have Valid data
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topTenAppInstall(apps = apps, size = 2)
        //then should return that order of top 10 install app
        val predictable = listOf(
            "Google Files",
            "Books",
        )
        assertEquals(predictable, result)
    }

    @Test// Test point #6
    fun should_ReturnNullWhenTheEnterNumberOfAppsIsZero() {
        //Given valid list of apps , Enter Number Of Apps want to Filter depend on it is equals to Zero
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topTenAppInstall(apps = apps, size = 0)
        //then should return Null
        assertNull(result)
    }

    @Test// Test point #6
    fun should_ReturnNullWhenTheEnterNumberOfAppsIsLessThenZero() {
        //Given valid list of apps , Enter Number Of Apps want to Filter depend on it Is Less than Zero
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topTenAppInstall(apps = apps, size =  -1)
        //then should return Null
        assertNull(result)
    }
}