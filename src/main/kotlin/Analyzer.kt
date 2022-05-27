import kotlin.math.roundToInt

class Analyzer() {

    fun topTenAppInstall(appDataList: List<App>): List<String>? =
        if (appDataList.isNotEmpty())
            appDataList.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data.appName }
                .toSet()
                .take(10)
                .toList()
        else null

    fun findOldestApp(apps: List<App>): String {
        return if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else "-1"
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

}