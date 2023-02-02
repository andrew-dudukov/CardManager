package andrew.dudukov.cardmanager.ui.screen.transaction.list

import andrew.dudukov.cardmanager.core.coroutines.AppDispatchers
import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.repository.transaction.TransactionRepository
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    appDispatchers: AppDispatchers,
    private val transactionRepository: TransactionRepository,
    private val mapper: Mapper<TransactionDB, Transaction>
) : ViewModel() {

    val transactions: Flow<PagingData<Transaction>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = false,
            initialLoadSize = 20,
            maxSize = 30
        )
    ) { transactionRepository.transactionPagingSource() }.flow
        .map { pagingData -> pagingData.map(mapper::map) }
        .flowOn(appDispatchers.io)
        .cachedIn(viewModelScope)
}