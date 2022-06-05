import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.convertStringToSizeUnit() =
    if (this == "Varies with device"){
        0.0
    }
    else if (this.contains("M")) {
        this.replace("M", "").toDouble()
    } else if (this.contains("k")) {
        this.replace("k", "").toDouble() / 1024.0
    } else if (this.contains("G")) {
        this.replace("G", "").toDouble() * 1024.0
    } else {
    0.0
    }

fun String.convertStringToLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("MMMM d yyyy"))
}