package parser

import utilities.Constant
import utilities.convertStringToDate
import App
import java.io.File

class AppParser {
    fun parseFile(fileName: String): List<App> {
        val appList = mutableListOf<App>()
        File(fileName).apply { ->
            if (this.exists()) {
                this.forEachLine { line ->
                    appList.add(addApp(line))
                }
            }
        }
        return appList.distinctBy { Pair(it.appName, it.company) }
    }

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
            requiresAndroid = mList[Constant.ColumnIndex.REQUIRED_ANDROID]
        )
    }
}