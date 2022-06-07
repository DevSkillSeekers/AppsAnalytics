package model

import java.time.LocalDate

/**
 *  This class model is for csv file and json file
 */
data class App(
    val appName: String,
    val company: String,
    val category: String,
    val updatedDate: LocalDate,
    val size: Double,
    val installs: Long,
    val currentVersion: Any?,
    val requiresAndroid: Double?
)