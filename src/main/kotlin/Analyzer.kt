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


    fun findOldestApp(apps: List<App>): String {
        return if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else "-1"
    }

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double{
        return apps.filter { it.category.contains(categoryName,true) }
            .size.div(apps.size.toDouble()).times(100)
            .times(100).roundToInt().toDouble() / 100
    }

}