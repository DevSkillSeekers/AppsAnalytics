package parser

import utilities.Constant
import utilities.convertStringToDate
import App
import utilities.convertToDouble
import java.io.File

class AppParser {
    /**
     * @param fileName is string represent fileName for DataSet
     * @return list of all app inside DataSet without repetition
     * */
    fun parseFile(fileName: String): List<App> {
        val appList = mutableListOf<App>()
        File(fileName).apply {
            if (this.exists()) {
                this.forEachLine { line ->
                    appList.add(addApp(line))
                }
            }
        }
        return appList.distinctBy { Pair(it.appName, it.company)}
    }

    /**
     * @param s is line of dataSet
     * @return object of app
     * */
    private fun addApp(s: String): App {
        val mList = s.split(",")
        return App(
            appName = mList[Constant.ColumnIndex.APP_NAME],
            company = mList[Constant.ColumnIndex.COMPANY],
            category = mList[Constant.ColumnIndex.CATEGORY],
            updatedDate = convertStringToDate(mList[Constant.ColumnIndex.UPDATE_DATE]),
            size = mList[Constant.ColumnIndex.SIZE],
            installs = (mList[Constant.ColumnIndex.INSTALLS]).toLong(),
            currentVersion = mList[Constant.ColumnIndex.CURRENT_VERSION],
            requiresAndroid = convertToDouble(mList[Constant.ColumnIndex.REQUIRED_ANDROID])
        )
    }
}