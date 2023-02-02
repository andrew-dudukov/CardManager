package andrew.dudukov.cardmanager.data.repository.transaction

import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import androidx.paging.PagingSource

interface TransactionRepository {

    suspend fun fetchLastTransactions(number: Int): List<TransactionDB>

    fun transactionPagingSource(): PagingSource<Int, TransactionDB>
}