package parser

import utilities.Constant
import convertStringToDate
import App
import java.io.File
import java.math.BigInteger

class AppParser {

    fun parseFile(fileName: String): List<App> {
        val appList = mutableListOf<App>()
        val file = File(fileName)
        if (file.exists()) {
            file.forEachLine {
                    appList.add( addApp(it))
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
                 installs = (mList[Constant.ColumIndex.INSTALLS].toBigIntegerOrNull() ?: 0) as BigInteger,
                 currentVersion = mList[Constant.ColumIndex.CURRENT_VERSION],
                 requiresAndroid = mList[Constant.ColumIndex.REQUIRED_ANDROID]
             )

    }
}