fun main(args: Array<String>) {
    val parser = AppParser()
    val appList = parser.parseFile("google_play.csv")
    val analyzer = Analyzer(appList)

    print("The Oldest App is:\n${analyzer.findOldestApp()}")

}