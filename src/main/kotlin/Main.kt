import parser.AppParser
import utilities.Constant

fun main(args: Array<String>) {
    val apps = AppParser().parseFile(Constant.FILE_NAME)

    for(i in 0..10){
        println(apps[i].updatedDate.toString())
    }
}