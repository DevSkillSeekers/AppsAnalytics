import parser.CSVParser
import parser.JSONParser
import utilities.Constant
import utilities.Converter

fun main() {

    val apps = CSVParser(Constant.FILE_NAME,Converter()).getAllApps()
    val analyzer = Analyzer(Converter())

    val appsJson = JSONParser(Constant.FILE_NAME_JSON,Converter()).getAllApps()

    println(
        "1# How many apps were developed by Google in the dataset? " +
                "\n${analyzer.findNumberOfAppsByCompanyName(apps, "Google")}"
    )
    println("--------------------------------------------")
    println(
        "2# What is the percentage of Medical apps?" +
                "\n%${analyzer.getPercentageOfCategory(apps, "Medical")}"
    )
    println("--------------------------------------------")
    println(
        "3# What is the oldest app in the dataset." +
                "\n${analyzer.findOldestApp(apps)}"
    )
    println("--------------------------------------------")
    println(
        "4# What is the percentage of apps running on android 9 and up only?" +
                "\n%${analyzer.getPercentageAppsRunningOnSpecificVersion(apps, 9.0)}"
    )
    println("--------------------------------------------")
    println(
        "5# What are the largest 10 apps in the dataset?\n" +
                "${analyzer.getLargestApp(apps, 10)}"
    )
    println("--------------------------------------------")
    println(
        "6# What are the top 10 installed apps.\n" +
            "${analyzer.topNAppsInstall(apps,10)}"
    )

    println("--------------------------------------------  List of apps in JSON format")
    println(
        "7# List of JSON \n" +
            "$appsJson"
    )

}