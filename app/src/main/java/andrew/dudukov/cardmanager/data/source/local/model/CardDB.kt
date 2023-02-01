package andrew.dudukov.cardmanager.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CardDB.TABLE_NAME)
data class CardDB constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "numbers")
    val numbers: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "currency")
    val currency: String
) {
    companion object {
        const val TABLE_NAME = "cards"
    }
}