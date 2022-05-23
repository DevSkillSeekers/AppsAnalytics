import java.io.File
class AppParser {

    fun parseFile(fileName: String): List<GoogleApp> {
        val appList = mutableListOf<GoogleApp>()
        val file = File(fileName)
        var header = 0
        if (file.exists()) {
            file.forEachLine {
                if (header >0 ){
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
                }else {
                    header++
                }
            }
        }
        return appList
    }
}