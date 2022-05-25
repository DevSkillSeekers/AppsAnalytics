class Analyzer() {

    fun findOldestApp(apps: List<App>): Double {
        return 0.0
    }


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


}