package andrew.dudukov.cardmanager.data.source.local.dao

import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("SELECT * FROM ${CardDB.TABLE_NAME}")
    fun getAll(): List<CardDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<CardDB>)
}