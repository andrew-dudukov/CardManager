package andrew.dudukov.cardmanager.ui.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val id: Long,
    val type: String,
    val numbers: String,
    val amount: String,
    val currency: String
) : Parcelable