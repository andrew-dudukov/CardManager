package andrew.dudukov.cardmanager.ui.model.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id: Long,
    val merchand: String,
    val category: Category,
    val amount: String,
    val currency: String,
    val timestamp: Int,
    val isPending: Boolean
) : Parcelable