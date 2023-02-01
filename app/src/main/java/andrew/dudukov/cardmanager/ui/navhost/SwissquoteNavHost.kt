package andrew.dudukov.cardmanager.ui.navhost

import andrew.dudukov.cardmanager.ui.extension.obtainArg
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import andrew.dudukov.cardmanager.ui.navhost.destination.Overview
import andrew.dudukov.cardmanager.ui.navhost.destination.TransactionDetails
import andrew.dudukov.cardmanager.ui.navhost.destination.Transactions
import andrew.dudukov.cardmanager.ui.screen.overview.OverviewScreen
import andrew.dudukov.cardmanager.ui.screen.transaction.detail.TransactionDetailsScreen
import andrew.dudukov.cardmanager.ui.screen.transaction.list.TransactionsScreen
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson

@Composable
fun SwissquoteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            OverviewScreen(
                onClickViewAllTransactions = {
                    navController.navigate(Transactions.route)
                },
                onClickViewTransactionDetails = { transaction ->
                    navController.navigateToTransactionDetails(transaction)
                },
            )
        }
        composable(route = Transactions.route) {
            TransactionsScreen { transaction ->
                navController.navigateToTransactionDetails(transaction)
            }
        }
        composable(
            route = TransactionDetails.route,
            arguments = TransactionDetails.arguments
        ) { navBackStackEntry ->
            val transaction = navBackStackEntry.arguments?.obtainArg<Transaction>(TransactionDetails.transactionArg)
            transaction?.let { TransactionDetailsScreen(it) }
        }
    }
}

private fun NavHostController.navigateToTransactionDetails(transaction: Transaction) {
    val args = Uri.encode(Gson().toJson(transaction))
    navigate("${TransactionDetails.routeWithoutArgs}/$args")
}