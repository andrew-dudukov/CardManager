package andrew.dudukov.cardmanager.data.mapper.transaction

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.ui.model.transaction.Category
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import javax.inject.Inject

class TransactionDBMapper @Inject constructor() : Mapper<TransactionDB, Transaction> {

    override fun map(data: TransactionDB): Transaction =
        data.run { Transaction(id, merchand, Category.typeOf(category), amount, currency, timestamp, isPending) }
}