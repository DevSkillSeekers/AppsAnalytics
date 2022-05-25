import kotlin.math.roundToInt

class Analyzer() {

    fun topTenAppInstall(appDataList: List<App>): List<String>? =
        if (appDataList.isNotEmpty())
            appDataList.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data.appName }
                .toSet()
                .take(10)
        else null

    fun findOldestApp(apps: List<App>): String {
        return if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else "-1"
    }

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double{
        return apps.filter { it.category.contains(categoryName,true) }
            .size.div(apps.size.toDouble()).times(100)
            .times(100).roundToInt().toDouble() / 100
    }

}