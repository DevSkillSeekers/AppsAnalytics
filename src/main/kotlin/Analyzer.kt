import model.App
import utilities.Converter
import utilities.convertToByte
import java.math.BigDecimal

class Analyzer (val converter:Converter) {


    fun findNumberOfAppsByCompanyName(apps: List<App>, companyName: String): Int {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            apps.count { it.company.contains(companyName.trim(), true) }
        } else {
            return -1
        }
    }

    fun findOldestApp(apps: List<App>): App? {
        if (apps.isEmpty()) {
            return null
        }
        return apps.minByOrNull { it.updatedDate }
    }


    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version: Double): Double =
        converter.calculatePercentage(
            apps.count { it.requiresAndroid != null && it.requiresAndroid == version },
            apps.size
        )

    fun getPercentageOfCategory(apps: List<App>, categoryName: String): Double {
        if (apps.isNotEmpty() && categoryName.isNotEmpty()) {
            return converter.calculatePercentage(
                apps.count { it.category.contains(categoryName.trim(), true) },
                apps.size
            )
        }
        return -1.0

    }


    fun getLargestApp(apps: List<App>, size: Int): List<App>? {
        if (apps.isNotEmpty() && size <= apps.size) {
            val list = mutableMapOf<App, BigDecimal>()

            apps.filterNot { it.size.contains("Varies", true) }
                .apply {
                    onEach {


                        val value = (it.size).convertToByte()

                        if (value != null) {
                            list[it] = value
                        }
                    }
                }
            return list.toList().sortedByDescending { (_, value) -> value }.toMap()
                .keys.map { it -> it }.toList().take(size)
        }
        return null
    }

    fun topNAppInstall(apps: List<App>, numberOfApps: Int): List<App>? {
        if (apps.isNotEmpty() && numberOfApps > 0) {
            return apps.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .take(numberOfApps)
                .toList()
        }
        return null
    }

    fun getLargestAppsSizeByCompanyName(apps: List<App>, companyName: String, numberOfApps: Int): List<App>? {
        if (apps.isEmpty() && companyName.isEmpty()) {
            return null
        }
        val list = apps.filter { it.company.lowercase().contains(companyName.lowercase()) }
        return getLargestApp(list, numberOfApps)
    }
}
