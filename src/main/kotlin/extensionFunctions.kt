
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