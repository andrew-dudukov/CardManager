package andrew.dudukov.cardmanager.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TransactionDB.TABLE_NAME)
data class TransactionDB constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "merchand")
    val merchand: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Int,
    @ColumnInfo(name = "pending")
    val isPending: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "transactions"
    }
}