package parser

import convertStringToDate
import convertStringToSizeUnit
import interfaces.DataSource
import model.App
import utilities.Constant
import utilities.Converter
import java.io.File

class CSVParser(private val fileName: String, private val converter: Converter) : DataSource {

    /**
     * @return list of apps after parsed from DataSet without repetition
     * */
    override fun getAllApps(): List<App> {
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

    /**
     * @return object of app
     * */
    private fun addApp(dataSetLine: String): App {
        val mList = dataSetLine.split(",")
        return App(
            appName = mList[Constant.ColumnIndex.APP_NAME],
            company = mList[Constant.ColumnIndex.COMPANY],
            category = mList[Constant.ColumnIndex.CATEGORY],
            updatedDate = mList[Constant.ColumnIndex.UPDATE_DATE].convertStringToDate(),
            size = mList[Constant.ColumnIndex.SIZE].convertStringToSizeUnit(),
            installs = mList[Constant.ColumnIndex.INSTALLS].toLong(),
            currentVersion = mList[Constant.ColumnIndex.CURRENT_VERSION],
            requiresAndroid = converter.convertToDouble(mList[Constant.ColumnIndex.REQUIRED_ANDROID])
        )
    }

}