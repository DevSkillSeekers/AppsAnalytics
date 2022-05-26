import kotlin.math.roundToInt
class Analyzer() {

    fun topTenAppInstall(appDataList: List<App>): List<String>? =
        if (appDataList.isNotEmpty())
            appDataList.groupBy { it.appName }.keys
                .associateWith { appInstallCount(it, appDataList) }
                .asSequence()
                .sortedByDescending { (_, value) -> value }
                .map { value -> value.key }
                .take(10)
                .toList()
        else null

    private fun appInstallCount(appName: String, appDataList: List<App>) =
        appDataList.first { first -> first.appName == appName }.installs

    fun findOldestApp(apps: List<App>): String? {
        return if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else null
    }

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double{
        return if (apps.isNotEmpty())
         apps.filter { it.category.contains(categoryName,true) }
            .size.div(apps.size.toDouble()).times(100)
            .times(100).roundToInt().toDouble() / 100
        else -1.0
    }
    fun findAppsByCompany(companyName: String,apps: List<App>):Int{

      return apps.filter { it.company.contains(companyName,true)}.size


    }

    fun getLargestApp(apps: List<App>,size:Int):List<App>?{
        if (apps.isNotEmpty()) {
            val list = mutableMapOf<App,Long>()

             apps.filterNot { it.size.contains("Varies", true) }
                 .apply {
                    onEach {
                       list[it] = convertToNumber(it.size)
                    }
                 }
            return list.toList().sortedByDescending { (_, value) -> value}.toMap().keys.toList().take(size)
        }
        return null
    }

    fun percentageAppsRunningOnAndroid9(apps: List<App>): String?{
        if (apps.isEmpty()) return null
        apps.forEach { it ->
            if (it.requiresAndroid.contains("9 and up"))
                return String.format("%.1f", 100.0 * apps.count {
                    it.requiresAndroid == "9 and up"
                } / apps.size)
        }
        return null
    }

    private fun convertToNumber(size:String): Long {
        when {
            size.endsWith("k", true)->{
                return size.substring(0,size.indexOf("k")).toLong()
            }
            size.endsWith("M", true)->{
                return (size.substring(0,size.indexOf("M")).toDouble()* 1000).toLong()
            }
            size.endsWith("G", true)->{
                return (size.substring(0,size.indexOf("G")).toDouble()* 1000000).toLong()
            }
        }
        return 0
    }
}