import model.App
import utilities.calculatePercentage
import utilities.convertToByte
import java.math.BigDecimal

class Analyzer {

    /**
     * @param apps is a list of app class
     * @param companyName is string
     * @return number of apps with given companyName.
     * */
    fun findNumberOfAppsByCompanyName(apps: List<App>, companyName:String):Int {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            apps.count { it.company.contains(companyName.trim(), true) }
        } else {
            -1
        }
    }

    /**
     * @param apps is a list of App class
     * @return the oldest app in given list.
     * */
    fun findOldestApp(apps: List<App>): App? =
        if (apps.isNotEmpty()) {
            apps.minByOrNull { it.updatedDate }} else null

    /**
     * @param apps is a list of app class
     * @param version is string of android version
     * @return the parentage of given version.
     * */
    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version:Double): Double =
        calculatePercentage(apps.count{ it.requiresAndroid != null && it.requiresAndroid == version }, apps.size)


    /**
     * @param apps is a list of app class
     * @param categoryName is string of category that we need know
     * @return a percentage of given category from give list.
     * */
    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double =
        if (apps.isNotEmpty() && categoryName.isNotEmpty())
            calculatePercentage(
                apps.count { it.category.contains(categoryName.trim(), true) },
                apps.size)
        else -1.0


    fun getLargestApp(apps: List<App>,size:Int):List<App>?{
        if (apps.isNotEmpty() && size <= apps.size ) {
            val list = mutableMapOf<App,BigDecimal>()

            apps.filterNot { it.size.contains("Varies", true) }
                .apply {
                    onEach {
                        val value = convertToByte(it.size)
                        if(value!=null) {
                            list[it] = value
                        }
                    }
                }
            return list.toList().sortedByDescending { (_, value) -> value}.toMap()
                .keys.map { it-> it }.toList().take(size)
        }
        return null
    }

    /**
     * @param apps is a list of app class
     * @param size is Integer to give the user free to enter any number to return top install app depend on it
     * @return a top ten app install from give list if the list is not null or empty
     * */
    fun topNAppInstall(apps: List<App>, size: Int): List<App>? =
        if (apps.isNotEmpty() && size > 0)
            apps.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data }
                .take(size)
                .toList()
        else null
}