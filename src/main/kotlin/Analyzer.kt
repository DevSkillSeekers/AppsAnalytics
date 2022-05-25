import kotlin.math.roundToInt

class Analyzer() {

    fun findOldestApp(apps: List<App>): String {
        return if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else "-1"
    }

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double{
        return apps.filter { it.category.contains(categoryName,true) }
            .size.div(apps.size.toDouble()).times(100)
            .times(100).roundToInt().toDouble() / 100
    }

}