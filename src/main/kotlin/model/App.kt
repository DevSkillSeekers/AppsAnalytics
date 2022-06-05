package model

import java.util.*

data class App(
    val appName: String,
    val company: String,
    val category: String,
    val updatedDate: Date,
    val size: Double,
    val installs: Long,
    val requiresAndroid: Double?
)