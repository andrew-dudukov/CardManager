package andrew.dudukov.cardmanager.ui.screen.common

import andrew.dudukov.cardmanager.R
import andrew.dudukov.cardmanager.ui.extension.displayFormat
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun TransactionItem(
    transaction: Transaction,
    containerColor: Color = Color.Transparent,
    onClickViewTransactionDetails: (Transaction) -> Unit
) {
    val amount = (transaction.amount.toDoubleOrNull() ?: 0.0).displayFormat()
    Card(
        onClick = { onClickViewTransactionDetails(transaction) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            val (icon, title, value, status, arrowForward) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(icon) {
                        linkTo(
                            start = parent.start,
                            end = title.start,
                            top = parent.top,
                            bottom = parent.bottom,
                            startMargin = 8.dp
                        )
                    }
                    .background(MaterialTheme.colorScheme.background, CircleShape)
                    .padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(transaction.category.iconRes),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(24.dp),
                    tint = Color.Black.copy(alpha = if (transaction.isPending) 0.5f else 1.0f)
                )
            }
            Text(
                text = transaction.merchand,
                modifier = Modifier
                    .constrainAs(title) {
                        linkTo(
                            start = icon.end,
                            end = value.start,
                            top = icon.top,
                            bottom = icon.bottom,
                            startMargin = 8.dp,
                            endMargin = 8.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
            )
            Text(
                modifier = Modifier
                    .constrainAs(value) {
                        linkTo(start = title.end, end = arrowForward.start, top = title.top, bottom = title.bottom)
                        width = Dimension.wrapContent
                    },
                text = "-${amount} ${transaction.currency}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black.copy(alpha = if (transaction.isPending) 0.5f else 1.0f)
                )
            )
            Icon(
                modifier = Modifier
                    .constrainAs(arrowForward) {
                        linkTo(start = value.end, end = parent.end, top = value.top, bottom = value.bottom)
                    },
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
            Text(
                modifier = Modifier
                    .constrainAs(status) {
                        end.linkTo(value.end)
                        top.linkTo(value.bottom)
                    },
                text = stringResource(id = if (transaction.isPending) R.string.pending else R.string.executed),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Black.copy(alpha = if (transaction.isPending) 0.5f else 1.0f)
                )
            )
        }
    }
}