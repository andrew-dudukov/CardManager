package andrew.dudukov.cardmanager.data.mapper.transaction

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.data.source.remote.model.TransactionResponse
import javax.inject.Inject

class TransactionResponseMapper @Inject constructor() : Mapper<TransactionResponse, TransactionDB> {

    override fun map(data: TransactionResponse): TransactionDB =
        data.run { TransactionDB(id, merchand, category, amount, currency, timestamp) }
}