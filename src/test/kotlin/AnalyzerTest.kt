import model.App
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import utilities.Converter
import utilities.TestConstant
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AnalyzerTest {

    private lateinit var analyzer: Analyzer
    private lateinit var apps: MutableList<App>

    @BeforeAll
    fun setup() {
        analyzer = Analyzer(Converter())
    }

    @BeforeEach
    fun initialize() {
        apps=  mutableListOf()
    }

    private fun setList(testSet:Int =0):MutableList<App>{
        val  appList=  mutableListOf<App>()
        when(testSet){
           TestConstant.CHANGE_SIZE_UPPER_LOWER_CASE ->{
               appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M", 500000, "1.0.0", 4.4))
               appList.add( App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30k", 30, "1.0.0", 9.0))
               appList.add(App("Google Photo", "Google ", "Libraries & Demo", Date("2/1/2000"), "5.5g", 500, "1.0.0", 6.0))
               appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5g", 1000000, "1.0.0", 9.0))
           }
           else ->{
               appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M", 500000, "1.0.0", 4.4))
               appList.add(App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30M", 30, "1.0.0", 9.0))
               appList.add(App("Google Photo", "Google", "Libraries & Demo", Date("2/1/2000"), "50M", 500, "1.0.0", 6.0))
               appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5M", 1000000, "1.0.0", 9.0))
           }
       }
        return appList
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameEqualsToGoogle(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(2, result)
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpaceAtTheEnd(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName contains space at the end
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google ")
        //then check the result
        assertEquals(2, result)
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpace(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName contains space
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"goo gle")
        //then check the result
        assertEquals(0, result)
    }

    @Test //Test Point #1
    fun should_ReturnZero_When_CompanyNameNotFound(){
        //Given list of apps
        apps = setList()
        ////when calculate number of apps that developed by companyName doesn't exist
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"sfkjnc")
        //then check the result
        assertEquals(0, result)
    }

    @Test //Test Point #1
    fun should_ReturnMinus1_When_CompanyNameEmpty(){
        //Given list of apps
        apps = setList()
        //when calculate number of apps that developed by companyName is empty
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"")
        //then check the result
        assertEquals(-1, result)

    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameInUpperCase(){
        //Given list of apps
        apps = setList()
        //when calculate number of apps that developed by companyName in upper case
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        //then check the result
        assertEquals(2, result)

    }

    @Test //Test Point #1
    fun should_ReturnMinus1_When_ListEmpty(){
        //Given empty list of apps
        apps
        //when calculate number of apps that developed by companyName
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        //then check the result
        assertEquals(-1, result)

    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfGivenCategory_When_ValidList() {
        //Given valid list of apps and valid categoryName
        apps = setList()
        val categoryName = "Medical"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfGivenCategory_When_CategoryWithUpperOrLowerCase() {
        //Given list of apps and upperCase categoryName
        apps = setList()
        val categoryName = "MEDICAL"
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps,categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfGivenCategory_When_CategoryWithSpace() {
        //Given valid list of apps and category name with space
        apps = setList()
        val categoryName = " MEDICAL "
        //when calculate % of medical apps in list
        val result = analyzer.getPercentageOfCategory(apps, categoryName)
        //then check the result
        assertEquals(25.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_CalculatePercentageOfCategoryWithEmptyList() {
        //Given empty list of apps and valid category
        apps
        val categoryName = "Medical"
        //when calculate % of medical apps in emptyList
        val result = analyzer.getPercentageOfCategory(apps,categoryName)
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnZero_When_CategoryNotFound() {
        //Given valid list of apps and category name not in list
        apps = setList()
        val category = "Shopping"
        //when calculate % of category that not in list
        val result = analyzer.getPercentageOfCategory(apps,category)
        //then check the result
        assertEquals(0.0, result)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_CategoryIsEmpty() {
        //Given valid list of apps and empty category name
        apps = setList()
        //when calculate % empty category name
        val result = analyzer.getPercentageOfCategory(apps, "")
        //then check the result
        assertEquals(-1.0, result)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_FindOldestAppForEmptyList() {
        //Given emptyList
        apps
        //when search for the oldest app
        val result = analyzer.findOldestApp(apps)
        //then check the result
        assertNull(result)
    }

    @Test // Test point #3
    fun should_Return_TheOldestApp_whenHasValidList() {
        //Given valid list
        apps = setList()
        //when search for the oldest app
        val result = analyzer.findOldestApp(apps)
        //then check the result
        assertEquals(apps[2], result)
    }

    @Test // Test point #4
    fun should_ReturnMinus1_When_CalculateRunningOnSpecificVersion_WithEmptyAppList() {
        //Given emptyList of apps and valid version
        apps
        val version = 9.0
        //when calculate percentage of empty list
        val result = analyzer.getPercentageAppsRunningOnSpecificVersion(apps ,version)
        //then check the result
        assertEquals(-1.0,result)
    }

    @Test // Test point #4
    fun should_Return_PercentageAppsRunningOnSpecificVersion_when_HasValidList() {
        //Given valid list of apps and valid version
        apps = setList()
        val version = 9.0
        //when calculate percentage
        val result = analyzer.getPercentageAppsRunningOnSpecificVersion(apps,version)
        //then check the result
        assertEquals(50.0, result)
    }

    @Test // Test point #4
    fun should_ReturnZero_when_NoAppRunningOnSpecificVersion() {
        //Given valid list of apps and valid version
        apps = setList()
        val version = 10.0
        //when calculate percentage
        val result = analyzer.getPercentageAppsRunningOnSpecificVersion(apps,version)
        //then check the result
        assertEquals(0.0, result)
    }

    @Test// Test point #5
    fun should_Return_TopLargestApps() {
        //given valid list and valid size
        apps = setList()
        val numberOfApp = 4
        //when search for top 4 largest app
        val largestFourApps = analyzer.getLargestApp(apps,numberOfApp)
        //then should return that order of top 4 largest app that compares the size with unit KB
        val expectedResultValue = listOf(apps[2], apps[1],apps[0],apps[3])
        assertEquals(expectedResultValue, largestFourApps)
    }

    @Test// Test point #5
    fun should_ReturnNull_TopLargestApps_When_ListEmpty() {
        //given empty list
        apps
        val numberOfApp= 4
        //when search for top 4 largest app
        val largestFourApps  = analyzer.getLargestApp(apps,numberOfApp)
        //then should return -1
        assertNull(largestFourApps)
    }

    @Test// Test point #5
    fun should_ReturnNull_TopLargestApps_When_RankIsBiggerThanSize() {
        //given valid list and wrong rank that is bigger than size list
        apps = setList()
        val numberOfApp= 5
        //when search for top 10 largest app
        val largestTenApps  = analyzer.getLargestApp(apps,numberOfApp)
        //then should return -1
        assertNull(largestTenApps)
    }

    @Test// Test point #5
    fun should_Return_TopLargestApps_When_Size_LoweUpperCases() {
        //given valid list with Capital/Small size Units
        apps = setList(TestConstant.CHANGE_SIZE_UPPER_LOWER_CASE)
        val numberOfApp= 2
        //when search for top 2 largest app
        val largestTwoApps = analyzer.getLargestApp(apps,numberOfApp)
        //then should return
        val expectedResultValue = listOf(apps[2],apps[3])
        assertEquals(expectedResultValue,largestTwoApps)
    }

    @Test// Test point #6
    fun should_ReturnNull_When_TheListIsEmpty() {
        //Given empty list
        apps
        //when search for find top ten app Install
        val result = analyzer.topNAppInstall(apps = apps, numberOfApps = 10)
        //then should return null
        assertNull(result)
    }

    @Test// Test point #6
    fun should_ReturnTopAppInstall_WhenHasValidData() {
        //Given list have Valid data
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topNAppInstall(apps = apps, numberOfApps = 2)
        //then should return that order of top 10 install app
        val predictable = listOf(
            apps.last(),
            apps.first(),

        )
        assertEquals(predictable, result)
    }

    @Test// Test point #6
    fun should_ReturnNullWhenTheEnterNumberOfAppsIsZero() {
        //Given valid list of apps , Enter Number Of Apps want to Filter depend on it is equals to Zero
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topNAppInstall(apps = apps, numberOfApps = 0)
        //then should return Null
        assertNull(result)
    }

    @Test// Test point #6
    fun should_ReturnNullWhenTheEnterNumberOfAppsIsLessThenZero() {
        //Given valid list of apps , Enter Number Of Apps want to Filter depend on it Is Less than Zero
        apps = setList()
        //when search for top 3 app install
        val result = analyzer.topNAppInstall(apps = apps, numberOfApps =  -1)
        //then should return Null
        assertNull(result)
    }

}