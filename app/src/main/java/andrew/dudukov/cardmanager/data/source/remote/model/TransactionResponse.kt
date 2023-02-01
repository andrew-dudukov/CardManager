package andrew.dudukov.cardmanager.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("merchand") val merchand: String,
    @SerializedName("category") val category: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("timestamp") val timestamp: Int
)