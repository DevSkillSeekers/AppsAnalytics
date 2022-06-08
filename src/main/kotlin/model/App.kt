package model

import java.util.*

/**
 *  This class model is for csv file and json file
 */
data class App(
    val appName: String,
    val company: String,
    val category: String,
    val updatedDate: Date,
    val size: Double,
    val installs: Long,
    val currentVersion: Any?,
    val requiresAndroid: Double?
)