import model.App
import utilities.Converter

class Analyzer(private val converter: Converter) {

    /**
     * @return number of apps with given companyName.
     * */
    fun findNumberOfAppsByCompanyName(apps: List<App>, companyName: String): Int {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            return apps.count { it.company.contains(companyName.trim(), true) }
        } else {
            -1
        }
    }

    /**
     * @return the oldest app in given list.
     * */
    fun findOldestApp(apps: List<App>): String? {
        return if (apps.isNotEmpty()) {
            apps.minByOrNull { it.updatedDate }!!.appName
        } else {
            null
        }
    }


    /**
     * @return the parentage of given version.
     * */
    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version: Double): Double {
        return converter.calculatePercentage(
            apps.count { it.requiresAndroid != null && it.requiresAndroid == version },
            apps.size
        )
    }


    /**
     * @return a percentage of given category from give list.
     * */
    fun getPercentageOfCategory(apps: List<App>, categoryName: String): Double {
        return if (apps.isNotEmpty() && categoryName.isNotEmpty()) {
            converter.calculatePercentage(
                apps.count { it.category.contains(categoryName.trim(), true) },
                apps.size
            )
        } else {
            -1.0
        }
    }


    fun getLargestApp(listOfGooglePlayApp: List<App>, size: Int): List<String>? {
        val listOfAppName: MutableList<String> = mutableListOf()
        if (listOfGooglePlayApp.size > size) {
            listOfGooglePlayApp.sortedByDescending { it.size }.subList(0, size).forEach {
                listOfAppName.add(it.appName)
            }
        } else {
            listOfGooglePlayApp.sortedByDescending { it.size }.forEach {
                listOfAppName.add(it.appName)
            }
        }
        if (listOfAppName.size == 0) {
            return null
        }
        return listOfAppName
    }

    /**
     * @param rankNumber is Integer to give the user free to enter any number to return top installed apps depend on it
     * @return a top N apps install from given list if the list is not null or empty
     * */
    fun topNAppsInstall(apps: List<App>, rankNumber: Int): List<String>? =
        if (apps.isNotEmpty() && rankNumber > 0) {
            apps.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data.appName }
                .take(rankNumber)
                .toList()
        } else {
            null
        }
}