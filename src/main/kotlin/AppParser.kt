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
                val updatedLong = convertToDate(updatedString)

                val app = GoogleApp(
                    appName = appName,
                    company = company,
                    category = category,
                    updatedDate = updatedString,
                    updatedDateLong = updatedLong,
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

    private fun convertToDate(dateString: String): Long {
        val months = listOf("January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December")

        val dateList = dateString.split(" ")
        val indexOfMonth = months.indexOf(dateList[0])
        val day = dateList[1].toInt()
        val year = dateList[2].toInt()

        var dateNewFormat = if (indexOfMonth < 9) "0${indexOfMonth+1}" else "${indexOfMonth+1}"
        dateNewFormat += if (day<10) "-0$day-${year}" else "-$day-${year}"

        val formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu", Locale.ENGLISH)
        val date = LocalDate.parse(dateNewFormat, formatter)
        val millisecond: Long = date.atStartOfDay(ZoneOffset.MIN).toInstant().toEpochMilli()
        return millisecond
    }

}