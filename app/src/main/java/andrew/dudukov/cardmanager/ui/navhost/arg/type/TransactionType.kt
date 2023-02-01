package andrew.dudukov.cardmanager.ui.navhost.arg.type

import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class TransactionType : NavType<Transaction>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Transaction? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Transaction::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Transaction {
        return Gson().fromJson(value, Transaction::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Transaction) {
        bundle.putParcelable(key, value)
    }
}