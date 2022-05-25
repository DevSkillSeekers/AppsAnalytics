import parser.AppParser
import utilities.Constant

fun main(args: Array<String>) {
    val apps = AppParser().parseFile(Constant.FILE_NAME)
    val analyzer = Analyzer()

    println("What is the oldest app in the dataset." +
            "\n${analyzer.findOldestApp(apps)}")

    println("the percentage of Medical apps:" +
            "\n${analyzer.getPercentageOfCategory(apps,"Medical")}")

    println("largest Apps: ")
    analyzer.getLargestApp(apps,20)?.onEach { println("${it.appName } , ${it.size}") }

}