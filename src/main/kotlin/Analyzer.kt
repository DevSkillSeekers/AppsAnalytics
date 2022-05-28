import utilities.calculatePercentage
import utilities.convertToNumber

class Analyzer {

    /**
     * @param apps is a list of App class
     * @return the oldest app in given list.
     * */
    fun findOldestApp(apps: List<App>): String? =
        if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else null

    /**
     * @param apps is a list of app class
     * @param version is string of android version
     * @return the parentage of given version.
     * */
    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version:Double): Double =
        calculatePercentage(apps.filter{ it.requiresAndroid != null && it.requiresAndroid == version }.size, apps.size)


    /**
     * @param apps is a list of app class
     * @param categoryName is string of category that we need know
     * @return a percentage of given category from give list.
     * */
    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double =
        if (apps.isNotEmpty() && categoryName.isNotEmpty())
            calculatePercentage(
                apps.filter { it.category.contains(categoryName.trim(), true) }.size,
                apps.size)
        else -1.0


    fun getLargestApp(apps: List<App>,size:Int):List<String>?{
        if (apps.isNotEmpty()) {
            val list = mutableMapOf<App,Long>()

             apps.filterNot { it.size.contains("Varies", true) }
                 .apply {
                    onEach {
                       list[it] = convertToNumber(it.size)
                    }
                 }
            return list.toList().sortedByDescending { (_, value) -> value}.toMap()
                .keys.map { it-> it.appName }.toList().take(size)
        }
        return null
    }

    fun topTenAppInstall(appDataList: List<App>): List<String>? =
        if (appDataList.isNotEmpty())
            appDataList.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data.appName }
                .take(10)
                .toList()
        else null
}