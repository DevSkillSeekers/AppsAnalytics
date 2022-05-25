class Analyzer() {

    fun findOldestApp(apps: List<App>): App {
        return apps.sortedBy { it.updatedDate }[0]
    }

}