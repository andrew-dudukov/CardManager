package andrew.dudukov.cardmanager

import andrew.dudukov.cardmanager.ui.navhost.CardManagerNavHost
import andrew.dudukov.cardmanager.ui.navhost.destination.Overview
import andrew.dudukov.cardmanager.ui.navhost.destination.TransactionDetails
import andrew.dudukov.cardmanager.ui.navhost.destination.Transactions
import andrew.dudukov.cardmanager.ui.navhost.destination.cardManagerScreens
import andrew.dudukov.cardmanager.ui.theme.SwissquoteTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwissquoteApp()
        }
    }
}

@Composable
fun SwissquoteApp() {
    SwissquoteTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = cardManagerScreens.find { it.route == currentDestination?.route } ?: Overview
        val titleRes = when (currentScreen) {
            Transactions -> R.string.transactions
            TransactionDetails -> R.string.transactions_details
            else -> null
        }
        Scaffold(
            topBar = {
                currentScreen.takeIf { currentScreen != Overview }?.let {
                    CenteredAppBar(
                        navController = navController,
                        titleRes = titleRes ?: R.string.app_name
                    )
                }
            }
        ) { innerPadding ->
            CardManagerNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun CenteredAppBar(navController: NavController, @StringRes titleRes: Int) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwissquoteApp()
}