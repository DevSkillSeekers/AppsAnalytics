package parser

import model.App
import utilities.Constant
import utilities.Converter
import java.io.File

class DataParser(private val fileName: String) {

    fun parseFile(): List<App> {
        val appList = mutableListOf<App>()
        File(fileName).apply {
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
        val converter = Converter()
        return App(
            appName = mList[Constant.ColumnIndex.APP_NAME],
            company = mList[Constant.ColumnIndex.COMPANY],
            category = mList[Constant.ColumnIndex.CATEGORY],
            updatedDate = converter.convertStringToDate(mList[Constant.ColumnIndex.UPDATE_DATE]),
            size = mList[Constant.ColumnIndex.SIZE],
            installs = mList[Constant.ColumnIndex.INSTALLS].toLong(),
            currentVersion = mList[Constant.ColumnIndex.CURRENT_VERSION],
            requiresAndroid = converter.convertToDouble(mList[Constant.ColumnIndex.REQUIRED_ANDROID])
        )
    }
}