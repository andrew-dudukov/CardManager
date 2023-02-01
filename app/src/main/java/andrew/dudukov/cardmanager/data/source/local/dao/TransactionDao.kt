package andrew.dudukov.cardmanager.data.source.local.dao

import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {

    @Query("SELECT * FROM ${TransactionDB.TABLE_NAME}")
    fun getAll(): List<TransactionDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<TransactionDB>)

    @Query("SELECT * FROM ${TransactionDB.TABLE_NAME} ORDER BY pending DESC LIMIT :number")
    fun getLastTransactions(number: Int): List<TransactionDB>

    @Query("SELECT * FROM ${TransactionDB.TABLE_NAME} ORDER BY pending DESC")
    fun getTransactionPagingSource(): PagingSource<Int, TransactionDB>
}