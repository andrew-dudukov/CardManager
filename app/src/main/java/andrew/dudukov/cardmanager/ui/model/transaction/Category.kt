package andrew.dudukov.cardmanager.ui.model.transaction

import andrew.dudukov.cardmanager.R
import androidx.annotation.DrawableRes

enum class Category(@DrawableRes val iconRes: Int, private val categoryName: String) {
    Shopping(R.drawable.ic_shopping, "shopping"),
    Service(R.drawable.ic_services, "service"),
    Unknown(R.drawable.ic_unknown, "unknown");

    companion object {
        fun typeOf(value: String) = values().find { it.categoryName == value } ?: Unknown
    }
}