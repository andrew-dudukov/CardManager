package andrew.dudukov.cardmanager.ui.screen.overview

import andrew.dudukov.cardmanager.core.coroutines.AppDispatchers
import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.repository.card.CardRepository
import andrew.dudukov.cardmanager.data.repository.transaction.TransactionRepository
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.ui.model.card.Card
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository,
    private val cardMapper: Mapper<CardDB, Card>,
    private val transactionMapper: Mapper<TransactionDB, Transaction>
) : ViewModel() {

    companion object {
        private const val PREVIEW_TRANSACTION_NUMBER = 5
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(OverviewViewModel::class.java.simpleName, throwable.stackTraceToString())
    }

    private val _cardsState = MutableStateFlow<List<Card>>(emptyList())
    val cardsState = _cardsState.asStateFlow()

    private val _transactionState = MutableStateFlow<List<Transaction>>(emptyList())
    val transactionState = _transactionState.asStateFlow()

    init {
        loadCards()
        loadTransactions()
    }

    private fun loadCards() {
        viewModelScope.launch(appDispatchers.io + exceptionHandler) {
            val cards = cardRepository.fetchCards()
            _cardsState.value = cardMapper.map(cards)
        }
    }

    private fun loadTransactions() {
        viewModelScope.launch(appDispatchers.io + exceptionHandler) {
            val transactions = transactionRepository.fetchLastTransactions(PREVIEW_TRANSACTION_NUMBER)
            _transactionState.value = transactionMapper.map(transactions)
        }
    }
}