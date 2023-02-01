package andrew.dudukov.cardmanager.data.source.local

import andrew.dudukov.cardmanager.data.source.local.dao.CardDao
import andrew.dudukov.cardmanager.data.source.local.dao.TransactionDao
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CardDB::class,
        TransactionDB::class,
    ],
    version = 1,
)
abstract class CardManagerDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    abstract fun transactionDao(): TransactionDao
}