package utilities


class Converter {
    fun calculatePercentage(dividend: Int, divisor: Int): Double =
        if (divisor != 0) {
            String.format("%.1f", 100.0 * dividend.div(divisor.toDouble())).toDouble()
        } else -1.0

    fun convertToDouble(version: String): Double? = version.split(" ").first().toDoubleOrNull()


}