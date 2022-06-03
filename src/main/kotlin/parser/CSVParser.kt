package parser

import interfaces.DataSource
import model.App
import utilities.Constant
import utilities.convertStringToDate
import utilities.convertToDouble
import java.io.File

class CSVParser(private val fileName: String) : DataSource {

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
            installs = mList[Constant.ColumnIndex.INSTALLS].toLong(),
            requiresAndroid = convertToDouble(mList[Constant.ColumnIndex.REQUIRED_ANDROID])
        )
    }

}