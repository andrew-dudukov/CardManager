package andrew.dudukov.cardmanager.ui.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Double.displayFormat(): String {
    val formatter = DecimalFormat("#,###.00")
    formatter.decimalFormatSymbols = DecimalFormatSymbols().apply {
        decimalSeparator = '.'
        groupingSeparator = ' '
    }
    return formatter.format(this)
}