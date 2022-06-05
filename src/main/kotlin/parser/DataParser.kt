package parser

import Interface.DataSource
import model.App
import utilities.*

import java.io.File

class DataParser(private val fileName: String,private  val  converter: Converter): DataSource {

    override
    fun getAllApps(): List<App> {
        val appList = mutableListOf<App>()
        File(fileName).apply {
            if (this.exists()) {
                this.forEachLine { line ->
                    appList.add(converter.convertStringWithCommaToApp(line))
                }
            }
        }
        return appList.distinctBy { Pair(it.appName, it.company) }
    }


}