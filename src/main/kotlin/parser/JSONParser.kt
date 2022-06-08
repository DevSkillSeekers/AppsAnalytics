package parser

import convertStringToLocalDate
import convertStringToSizeUnit
import interfaces.DataSource
import model.App
import org.json.JSONArray
import org.json.JSONObject
import utilities.Constant
import utilities.convertToDouble
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class JSONParser(private val fileName: String) : DataSource {
    override fun getAllApps(): List<App> {
        val appList = ArrayList<App>()
        val jsonFile = File(fileName)
        val jsonString = jsonFile.readText()
        val jsonArray = JSONArray(jsonString)
        jsonArray.forEach {
            val jsonObject = JSONObject(it.toString())
            val appName = jsonObject.getString(Constant.ColumnIndexConstant.APP_NAME)
            val appCompany = jsonObject.getString(Constant.ColumnIndexConstant.COMPANY)
            val appCategory = jsonObject.getString(Constant.ColumnIndexConstant.CATEGORY)
            val appUpdatedDate = stringToDate(jsonObject.getString(Constant.ColumnIndexConstant.UPDATE_DATE))
            val appSize = jsonObject.getString(Constant.ColumnIndexConstant.SIZE).convertStringToSizeUnit()
            val appInstalls = jsonObject.getLong(Constant.ColumnIndexConstant.INSTALLS)
            val appCurrentVersion = jsonObject.get(Constant.ColumnIndexConstant.CURRENT_VERSION)
            val appRequiresAndroid = convertToDouble(jsonObject.getString(Constant.ColumnIndexConstant.REQUIRED_ANDROID))
            appList.add(
                App(
                    appName,
                    appCompany,
                    appCategory,
                    appUpdatedDate,
                    appSize,
                    appInstalls,
                    appCurrentVersion,
                    appRequiresAndroid
                )
            )
        }
        return appList
    }
    private fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("MMMM d yyyy"))
    }
}