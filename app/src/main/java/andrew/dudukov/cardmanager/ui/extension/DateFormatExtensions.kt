package andrew.dudukov.cardmanager.ui.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy, HH:mm", Locale.ENGLISH)
    return simpleDateFormat.format(this)
}