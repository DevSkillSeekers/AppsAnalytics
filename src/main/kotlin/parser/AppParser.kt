package parser

import utilities.Constant
import utilities.convertStringToDate
import App
import java.io.File
import java.math.BigInteger

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
        return appList
    }

    private fun addApp(s: String): App {
        val mList = s.split(",")
        return App(
            appName = mList[Constant.ColumIndex.APP_NAME],
            company = mList[Constant.ColumIndex.COMPANY],
            category = mList[Constant.ColumIndex.CATEGORY],
            updatedDate = convertStringToDate(mList[Constant.ColumIndex.UPDATE_DATE]),
            size = mList[Constant.ColumIndex.SIZE],
            installs = (mList[Constant.ColumIndex.INSTALLS]).toLong(),
            currentVersion = mList[Constant.ColumIndex.CURRENT_VERSION],
            requiresAndroid = mList[Constant.ColumIndex.REQUIRED_ANDROID]
        )

    }
}