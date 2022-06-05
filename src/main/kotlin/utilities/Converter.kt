package utilities

import model.App


class Converter {
    fun calculatePercentage(dividend: Int, divisor: Int): Double =
        if (divisor != 0) {
            String.format("%.1f", 100.0 * dividend.div(divisor.toDouble())).toDouble()
        } else -1.0

    fun convertToDouble(version: String): Double? = version.split(" ").first().toDoubleOrNull()
    fun convertStringWithCommaToApp(s: String): App {
        val mList = s.split(",")
        return App(
            appName = mList[Constant.ColumnIndex.APP_NAME],
            company = mList[Constant.ColumnIndex.COMPANY],
            category = mList[Constant.ColumnIndex.CATEGORY],
            updatedDate = mList[Constant.ColumnIndex.UPDATE_DATE].convertToDate(),
            size = mList[Constant.ColumnIndex.SIZE],
            installs = mList[Constant.ColumnIndex.INSTALLS].toLong(),
            currentVersion = mList[Constant.ColumnIndex.CURRENT_VERSION],
            requiresAndroid = convertToDouble(mList[Constant.ColumnIndex.REQUIRED_ANDROID])
        )
    }

}