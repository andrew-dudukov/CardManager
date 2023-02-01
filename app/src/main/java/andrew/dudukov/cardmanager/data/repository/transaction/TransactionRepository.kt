package andrew.dudukov.cardmanager.data.repository.transaction

import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import androidx.paging.PagingSource

interface TransactionRepository {

    suspend fun fetchLastTransactions(number: Int): List<Transaction>

    fun transactionPagingSource(): PagingSource<Int, TransactionDB>
}