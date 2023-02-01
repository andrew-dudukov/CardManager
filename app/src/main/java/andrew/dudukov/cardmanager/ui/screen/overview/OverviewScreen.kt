package andrew.dudukov.cardmanager.ui.screen.overview

import andrew.dudukov.cardmanager.R
import andrew.dudukov.cardmanager.ui.extension.displayFormat
import andrew.dudukov.cardmanager.ui.model.card.Card
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import andrew.dudukov.cardmanager.ui.screen.common.TransactionItem
import andrew.dudukov.cardmanager.ui.theme.Background
import andrew.dudukov.cardmanager.ui.theme.Blue
import andrew.dudukov.cardmanager.ui.theme.DarkGray
import andrew.dudukov.cardmanager.ui.theme.Gray
import andrew.dudukov.cardmanager.ui.theme.Orange
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
fun OverviewScreen(
    onClickViewAllTransactions: () -> Unit,
    onClickViewTransactionDetails: (Transaction) -> Unit,
) {
    val notImplementedToast = Toast.makeText(LocalContext.current, R.string.not_implemented_yet, Toast.LENGTH_SHORT)
    val viewModel = hiltViewModel<OverviewViewModel>()
    val cards by viewModel.cardsState.collectAsState()
    val transactions by viewModel.transactionState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CardCarousel(cards)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TitledButton(icRes = R.drawable.ic_padlock, textRes = R.string.lock_card) { notImplementedToast.show() }
            Spacer(modifier = Modifier.fillMaxWidth(fraction = 0.25f))
            TitledButton(icRes = R.drawable.ic_setting, textRes = R.string.setting) { notImplementedToast.show() }
        }
        LastTransactionsBlock(transactions, onClickViewAllTransactions, onClickViewTransactionDetails)
    }
}

@Composable
fun CardCarousel(cards: List<Card>) {
    val pagerState = rememberPagerState()
    val selectedCard by rememberUpdatedState(newValue = cards.getOrNull(pagerState.currentPage))
    Column {
        HorizontalPager(
            count = cards.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.3f),
            key = { cards[it].id },
            contentPadding = PaddingValues(horizontal = 32.dp),
        ) { page ->
            ConstraintLayout(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                },
            ) {
                val (text, image) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_silver_credit_card_background),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(image) {
                            linkTo(start = parent.start, end = parent.end, bottom = parent.bottom, top = parent.top)
                        }
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "•••• ${cards[page].numbers.takeLast(4)}",
                    modifier = Modifier.constrainAs(text) {
                        bottom.linkTo(image.bottom, 40.dp)
                        start.linkTo(image.start, margin = 32.dp)
                    },
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Background,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        selectedCard?.let { card ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val amount = (card.amount.toDoubleOrNull() ?: 0.0).displayFormat()
                Text(
                    text = "$amount ${card.currency}",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color.Black,
                    )
                )

            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.available_amount),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = DarkGray,
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun TitledButton(@DrawableRes icRes: Int, @StringRes textRes: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(4.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                painter = painterResource(icRes),
                contentDescription = null,
                modifier = Modifier.requiredSize(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            stringResource(id = textRes),
            style = MaterialTheme.typography.titleMedium.copy(color = Gray)
        )
    }
}

@Composable
fun LastTransactionsBlock(
    transactions: List<Transaction>,
    onClickViewAllTransactions: () -> Unit,
    onClickViewTransactionDetails: (Transaction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.card_transaction),
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
            )
            TextButton(
                onClick = onClickViewAllTransactions,
                contentPadding = PaddingValues(vertical = 12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.view_all),
                    style = MaterialTheme.typography.titleMedium.copy(color = Orange)
                )
            }
        }
        LazyColumn(contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 16.dp)) {
            items(items = transactions, key = { it.id }) {
                TransactionItem(it, onClickViewTransactionDetails = onClickViewTransactionDetails)
            }
        }
    }
}