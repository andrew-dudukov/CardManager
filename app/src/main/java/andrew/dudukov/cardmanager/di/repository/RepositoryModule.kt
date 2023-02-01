package andrew.dudukov.cardmanager.di.repository

import andrew.dudukov.cardmanager.data.repository.card.CardRepository
import andrew.dudukov.cardmanager.data.repository.card.CardRepositoryImpl
import andrew.dudukov.cardmanager.data.repository.transaction.TransactionRepository
import andrew.dudukov.cardmanager.data.repository.transaction.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository
}