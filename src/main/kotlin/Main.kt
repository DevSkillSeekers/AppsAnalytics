import parser.AppParser
import utilities.Constant

fun main(args: Array<String>) {
    val apps = AppParser().parseFile(Constant.FILE_NAME)
    val analyzer = Analyzer()

    println("1# How many apps were developed by Google in the dataset? "+
            "\n${analyzer.findNumberOfAppsByCompanyName(apps,"Google")}")

    println("--------------------------------------------")
    println("2# What is the percentage of Medical apps?" +
            "\n${analyzer.getPercentageOfCategory(apps,"Medical")}")
    println("--------------------------------------------")
    println("3# What is the oldest app in the dataset." +
            "\n${analyzer.findOldestApp(apps)}")
    println("--------------------------------------------")
    println("4# What is the percentage of apps running on android 9 and up only?"+
            "\n${analyzer.percentageAppsRunningOnAndroid9(apps)}")
    println("--------------------------------------------")
    println("5# What are the largest 10 apps in the dataset?\n" +
            "${analyzer.getLargestApp(apps,10)}")
    println("--------------------------------------------")
    println("6# What are the top 10 installed apps.\n" +
            "${analyzer.topTenAppInstall(apps)}")

}