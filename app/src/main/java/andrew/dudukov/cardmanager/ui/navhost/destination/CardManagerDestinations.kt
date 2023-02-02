package andrew.dudukov.cardmanager.ui.navhost.destination

import andrew.dudukov.cardmanager.ui.navhost.arg.type.TransactionType
import androidx.navigation.navArgument

interface CardManagerDestination {
    val route: String
}

object Overview : CardManagerDestination {
    override val route = "overview"
}

object Transactions : CardManagerDestination {
    override val route = "transactions"
}

object TransactionDetails : CardManagerDestination {
    const val transactionArg = "transaction"

    override val route = "transaction_details/{$transactionArg}"

    val routeWithoutArgs = "transaction_details"

    val arguments = listOf(navArgument(transactionArg) { type = TransactionType() })
}

val cardManagerScreens = listOf(Overview, Transactions, TransactionDetails)
