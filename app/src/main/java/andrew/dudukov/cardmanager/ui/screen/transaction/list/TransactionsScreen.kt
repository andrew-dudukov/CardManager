package andrew.dudukov.cardmanager.ui.screen.transaction.list

import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import andrew.dudukov.cardmanager.ui.screen.common.TransactionItem
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun TransactionsScreen(onClickViewTransactionDetails: (Transaction) -> Unit) {
    val viewModel = hiltViewModel<TransactionsViewModel>()
    val transactions = viewModel.transactions.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = transactions, key = { it.id }) { item ->
            item?.let { TransactionItem(it, Color.White, onClickViewTransactionDetails) }
        }
    }
}