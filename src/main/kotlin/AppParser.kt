import java.io.File
import java.time.LocalDate
import java.time.Year
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


class AppParser {

    fun parseFile(fileName: String): List<GoogleApp> {
        val appList = mutableListOf<GoogleApp>()
        val file = File(fileName)
        if (file.exists()) {
            file.forEachLine {
                val mList = it.split(",")

                val appName = mList[0]
                val company = mList[1]
                val category = mList[2]
                val updatedString = mList[3]
                val size = mList[4]
                val installs = mList[5].toBigInteger()
                val currentVersion = mList[6]
                val requiresAndroid = mList[7]

                val app = GoogleApp(
                    appName = appName,
                    company = company,
                    category = category,
                    updatedDate = updatedString,
                    size = size,
                    installs = installs,
                    currentVersion = currentVersion,
                    requiresAndroid = requiresAndroid
                )
                appList.add(app)
            }
        }
        return appList
    }

}