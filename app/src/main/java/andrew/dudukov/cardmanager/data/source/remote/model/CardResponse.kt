package andrew.dudukov.cardmanager.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("numbers") val numbers: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: String
)