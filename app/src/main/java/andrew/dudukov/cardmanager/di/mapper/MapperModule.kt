package andrew.dudukov.cardmanager.di.mapper

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.mapper.card.CardDBMapper
import andrew.dudukov.cardmanager.data.mapper.card.CardResponseMapper
import andrew.dudukov.cardmanager.data.mapper.transaction.TransactionDBMapper
import andrew.dudukov.cardmanager.data.mapper.transaction.TransactionResponseMapper
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.local.model.TransactionDB
import andrew.dudukov.cardmanager.data.source.remote.model.CardResponse
import andrew.dudukov.cardmanager.data.source.remote.model.TransactionResponse
import andrew.dudukov.cardmanager.ui.model.card.Card
import andrew.dudukov.cardmanager.ui.model.transaction.Transaction
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindCardResponseMapper(impl: CardResponseMapper): Mapper<CardResponse, CardDB>

    @Binds
    fun bindCardDBMapper(impl: CardDBMapper): Mapper<CardDB, Card>

    @Binds
    fun bindTransactionResponseMapper(impl: TransactionResponseMapper): Mapper<TransactionResponse, TransactionDB>

    @Binds
    fun bindTransactionDB(impl: TransactionDBMapper): Mapper<TransactionDB, Transaction>
}