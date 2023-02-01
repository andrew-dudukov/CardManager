package andrew.dudukov.cardmanager.ui.screen.overview

import andrew.dudukov.cardmanager.data.repository.card.CardRepository
import andrew.dudukov.cardmanager.data.repository.transaction.TransactionRepository
import andrew.dudukov.cardmanager.ui.model.card.Card
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    companion object {
        private const val PREVIEW_TRANSACTION_NUMBER = 10
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(OverviewViewModel::class.java.simpleName, throwable.localizedMessage)
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
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val cards = cardRepository.fetchCards()
            _cardsState.value = cards
        }
    }

    private fun loadTransactions() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val transactions = transactionRepository.fetchLastTransactions(PREVIEW_TRANSACTION_NUMBER)
            _transactionState.value = transactions
        }
    }
}