import model.App
import utilities.Converter
import java.math.BigDecimal

class Analyzer {

     lateinit var converter: Converter

    fun findNumberOfAppsByCompanyName(apps: List<App>, companyName:String):Int {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            apps.count { it.company.contains(companyName.trim(), true) }
        } else {
            -1
        }
    }

    fun findOldestApp(apps: List<App>): App? =
        if (apps.isNotEmpty()) {
            apps.minByOrNull { it.updatedDate }} else null

    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version:Double): Double =
        converter.calculatePercentage(apps.count{ it.requiresAndroid != null && it.requiresAndroid == version }, apps.size)

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double {
        if (apps.isNotEmpty() && categoryName.isNotEmpty()){
            return  converter.calculatePercentage(
                apps.count { it.category.contains(categoryName.trim(), true) },
                apps.size
            )
        }
        return -1.0

    }



    fun getLargestApp(apps: List<App>,size:Int):List<App>?{
        if (apps.isNotEmpty() && size <= apps.size ) {
            val list = mutableMapOf<App,BigDecimal>()

            apps.filterNot { it.size.contains("Varies", true) }
                .apply {
                    onEach {
                        val value = converter.convertToByte(it.size)
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

    fun topNAppInstall(apps: List<App>, size: Int): List<App>? {
        if (apps.isNotEmpty() && size > 0) {
            return  apps.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .take(size)
                .toList()
        }
        return  null
    }

    fun  getLargestAppSizeByCompanyName(apps: List<App>, companyName:String):BigDecimal?{
        TODO("Not implemented")
    }
}