package model

import java.util.*

class App(
    val appName:String,
    val company:String,
    val category:String,
    val updatedDate: Date,
    val size:String,
    val installs:Long,
    val requiresAndroid:Double?
)

//object App {
//    init {
//        val appName: String
//        val appCreatedCompany: String
//        val appCategory: String
//        val appUpdatedDate: Date
//        val appSize: String
//        val appInstalls: Long
//        val appCurrentVersion: String
//        val appRequiresAndroid: Double?
//
//    }
//}