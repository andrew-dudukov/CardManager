package andrew.dudukov.cardmanager.ui.screen.transaction.detail

import andrew.dudukov.cardmanager.R
import andrew.dudukov.cardmanager.ui.extension.displayFormat
import andrew.dudukov.cardmanager.ui.extension.formatDate
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun TransactionDetailsScreen(transaction: Transaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, CircleShape)
                    .padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(transaction.category.iconRes),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.merchand_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
            )
            Text(
                text = transaction.merchand,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.amount_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
            )
            Text(
                text = (transaction.amount.toDoubleOrNull() ?: 0.0).displayFormat(),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.currency_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
            )
            Text(
                text = transaction.currency,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.date_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
            )
            Text(
                text = Date(transaction.timestamp.toLong()).formatDate(),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
    }
}