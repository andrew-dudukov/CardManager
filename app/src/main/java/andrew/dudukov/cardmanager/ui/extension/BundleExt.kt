package andrew.dudukov.cardmanager.ui.extension

import android.os.Build
import android.os.Bundle

inline fun <reified T> Bundle.obtainArg(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}