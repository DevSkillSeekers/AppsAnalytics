import model.App
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import utilities.Converter
import java.util.*

@Suppress("DEPRECATION")
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

    private fun appsList(testSet:Int =0):MutableList<App>{
        val  appList=  mutableListOf<App>()
        when(testSet){
            TestConstant.CHANGE_SIZE_UPPER_LOWER_CASE ->{
                appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M".convertStringToSizeUnit(), 500000, 4.4,7.0))
                appList.add( App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30k".convertStringToSizeUnit(), 30, 9.0,8.0))
                appList.add(App("Google Photo", "Google ", "Libraries & Demo", Date("2/1/2000"), "5.5g".convertStringToSizeUnit(), 500, 6.0,9.0))
                appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5g".convertStringToSizeUnit(), 1000000, 9.0,7.0))
            }
            else ->{
                appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M".convertStringToSizeUnit(), 500000, 4.4,8.0))
                appList.add(App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30M".convertStringToSizeUnit(), 30, 9.0,8.0))
                appList.add(App("Google Photo", "Google", "Libraries & Demo", Date("2/1/2000"), "50M".convertStringToSizeUnit(), 500, 6.0,4.4))
                appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5M".convertStringToSizeUnit(), 1000000, 9.0,6.0))
            }
        }
        return appList
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameEqualsToGoogle(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        assertEquals(2, numberOfApps)
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpaceAtTheEnd(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"google ")
        assertEquals(2, numberOfApps)
    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameContainsSpaceInTheMiddle(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"goo gle")
        assertEquals(0, numberOfApps)
    }

    @Test //Test Point #1
    fun should_ReturnZero_When_CalculatingNumberOfAppsDevelopedByACompany_With_NonExistingCompanyName(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"sfkjnc")
        assertEquals(0, numberOfApps)
    }

    @Test //Test Point #1
    fun should_ReturnMinus1_When_CalculatingNumberOfAppsDevelopedByACompany_With_EmptyCompanyName(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"")
        assertEquals(-1, numberOfApps)

    }

    @Test //Test Point #1
    fun should_ReturnNumberOfAppsDevelopedByACompany_When_CompanyNameInUpperCase(){
        apps = appsList()
        val numberOfApps = analyzer.findNumberOfAppsByCompanyName(apps,"GOOGLE")
        assertEquals(2, numberOfApps)

    }

    @Test //Test Point #1
    fun should_ReturnMinus1_When_CalculatingNumberOfAppsDevelopedByACompany_With_EmptyList(){
        //Giving empty list of apps
        apps
        val result = analyzer.findNumberOfAppsByCompanyName(apps,"google")
        assertEquals(-1, result)

    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfMedicalApps_With_GivenCategoryAndValidList() {
        apps = appsList()
        val categoryName = "Medical"
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps, categoryName)
        assertEquals(25.0, percentageOfMedicalApps)
    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfMedicalApps_When_GivenCategoryWithUpperOrLowerCase() {
        apps = appsList()
        val categoryName = "MEDICAL"
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps,categoryName)
        assertEquals(25.0, percentageOfMedicalApps)
    }

    @Test // Test Point #2
    fun should_ReturnPercentageOfMedicalApps_When_GivenCategoryWithSpace() {
        apps = appsList()
        val categoryName = " MEDICAL "
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps, categoryName)
        assertEquals(25.0, percentageOfMedicalApps)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_CalculatingPercentageOfMedicalApps_With_GivenCategoryAndEmptyList() {
        //Giving empty list of apps
        apps
        val categoryName = "Medical"
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps,categoryName)
        assertEquals(-1.0, percentageOfMedicalApps)
    }

    @Test // Test Point #2
    fun should_ReturnZero_When_CalculatingPercentageOfMedicalApps_With_NonExistingCategory() {
        apps = appsList()
        val category = "Shopping"
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps,category)
        assertEquals(0.0, percentageOfMedicalApps)
    }

    @Test // Test Point #2
    fun should_ReturnMinus1_When_CalculatingPercentageOfMedicalApps_With_EmptyCategoryName() {
        apps = appsList()
        val percentageOfMedicalApps = analyzer.getPercentageOfCategory(apps, "")
        assertEquals(-1.0, percentageOfMedicalApps)
    }

    @Test // Test point #3
    fun should_ReturnNull_When_FindingOldestApp_With_GivenEmptyList() {
        //Giving empty list of apps
        apps
        val oldestApp = analyzer.findOldestApp(apps)
        assertNull(oldestApp)
    }

    @Test // Test point #3
    fun should_ReturnOldestApp_when_HasValidList() {
        apps = appsList()
        val oldestApp = analyzer.findOldestApp(apps)
        assertEquals("Google Photo", oldestApp)
    }

    @Test // Test point #4
    fun should_ReturnMinus1_When_CalculatingPercentageOfAppsRunningOnSpecificVersion_With_EmptyList() {
        //Giving empty list of apps
        apps
        val version = 9.0
        val percentageOfApps = analyzer.getPercentageAppsRunningOnSpecificVersion(apps ,version)
        assertEquals(-1.0,percentageOfApps)
    }

    @Test // Test point #4
    fun should_ReturnPercentageOfAppsRunningOnSpecificVersion_When_HasValidList() {
        apps = appsList()
        val version = 9.0
        val percentageOfApps = analyzer.getPercentageAppsRunningOnSpecificVersion(apps,version)
        assertEquals(50.0, percentageOfApps)
    }

    @Test // Test point #4
    fun shouldReturnZero_When_NoAppRunningOnSpecificVersion() {
        apps = appsList()
        val version = 10.0
        val percentageOfApps = analyzer.getPercentageAppsRunningOnSpecificVersion(apps,version)
        assertEquals(0.0, percentageOfApps)
    }

    @Test// Test point #5
    fun should_ReturnTopFourLargestApps() {
        apps = appsList()
        val rankNumber = 4
        val largestFourApps = analyzer.getLargestApp(apps,rankNumber)
        val expectedResultValue = listOf("Google Photo", "AD","Books", "Google Files")
        assertEquals(expectedResultValue, largestFourApps)
    }

    @Test// Test point #5
    fun should_ReturnNull_When_FindingTopFourLargestApps_With_EmptyList() {
        //Giving empty list of apps
        apps
        val rankNumber= 4
        val largestFourApps = analyzer.getLargestApp(apps,rankNumber)
        assertNull(largestFourApps)
    }

    @Test// Test point #5
    fun should_ReturnNull_When_FindingTopFourLargestApps_With_RankBiggerThanSize() {
        apps = appsList()
        val rankNumber= 5
        val largestFourApps = analyzer.getLargestApp(apps,rankNumber)
        assertNull(largestFourApps)
    }

    @Test// Test point #5
    fun should_Return_TopTwoLargestApps_When_SizeIsLowerOrUpperCase() {
        apps = appsList(TestConstant.CHANGE_SIZE_UPPER_LOWER_CASE)
        val rankNumber= 2
        val largestTwoApps = analyzer.getLargestApp(apps,rankNumber)
        val expectedResultValue = listOf("Google Photo","Google Files")
        assertEquals(expectedResultValue,largestTwoApps)
    }

    @Test// Test point #6
    fun should_ReturnNull_When_FindingTopTenInstalledApps_With_EmptyList() {
        //Giving empty list of apps
        apps
        val topTenInstalledApps = analyzer.topNAppsInstall(apps, 10)
        assertNull(topTenInstalledApps)
    }

    @Test// Test point #6
    fun should_ReturnTopThreeInstalledApps_When_HasValidList() {
        apps = appsList()
        val topThreeInstalledApps = analyzer.topNAppsInstall(apps, 2)
        val predictableResult = listOf(
            "Google Files",
            "Books",
        )
        assertEquals(predictableResult, topThreeInstalledApps)
    }

    @Test// Test point #6
    fun should_ReturnNull_When_FindingTopThreeInstalledApps_With_ZeroNumberOfApps() {
        apps = appsList()
        val topThreeInstalledApps = analyzer.topNAppsInstall(apps, 0)
        assertNull(topThreeInstalledApps)
    }

    @Test// Test point #6
    fun should_ReturnNull_WhenFindingTopThreeInstalledApps_With_NegativeValueEnteredForNumberOfApps() {
        apps = appsList()
        val topThreeInstalledApps = analyzer.topNAppsInstall(apps, -1)
        assertNull(topThreeInstalledApps)
    }

}

object TestConstant{
    const val CHANGE_SIZE_UPPER_LOWER_CASE = 1

}