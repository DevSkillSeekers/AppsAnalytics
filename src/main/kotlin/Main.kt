fun main(args: Array<String>) {
    val parser = AppParser()
    val appList = parser.parseFile("google_play.csv")
    for (i in 0..10){
        println("${appList[i].appName} date = ${appList[i].updatedDateLong}" )
    }


}