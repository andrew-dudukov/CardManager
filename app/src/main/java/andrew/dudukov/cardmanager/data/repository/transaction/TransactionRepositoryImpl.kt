package andrew.dudukov.cardmanager.data.repository.transaction

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.dao.TransactionDao
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.data.source.remote.SwissquoteRestAPI
import andrew.dudukov.cardmanager.data.source.remote.model.TransactionResponse
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: SwissquoteRestAPI,
    private val localDataSource: TransactionDao,
    private val networkMapper: Mapper<TransactionResponse, TransactionDB>,
    private val dbMapper: Mapper<TransactionDB, Transaction>
) : TransactionRepository {

    override suspend fun fetchLastTransactions(number: Int): List<Transaction> {
        refreshTransactions()
        val transactions = localDataSource.getLastTransactions(number)
        return dbMapper.map(transactions)
    }

    override fun transactionPagingSource(): PagingSource<Int, TransactionDB> =
        localDataSource.getTransactionPagingSource()

    private suspend fun refreshTransactions() {
        withContext(Dispatchers.IO) {
            val pending = async {
                val data = remoteDataSource.getPendingTransactions()
                networkMapper.map(data).map { it.copy(isPending = true) }
            }
            val executed = async {
                val data = remoteDataSource.getExecutedTransactions()
                networkMapper.map(data).map { it.copy(isPending = false) }
            }
            localDataSource.insert(pending.await() + executed.await())
        }
    }
}