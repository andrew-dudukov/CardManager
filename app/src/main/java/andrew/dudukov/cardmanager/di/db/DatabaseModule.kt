package andrew.dudukov.cardmanager.di.db

import andrew.dudukov.cardmanager.data.source.local.CardManagerDatabase
import andrew.dudukov.cardmanager.data.source.local.dao.CardDao
import andrew.dudukov.cardmanager.data.source.local.dao.TransactionDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "card_manager_app_db"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CardManagerDatabase {
        return Room.databaseBuilder(appContext, CardManagerDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCardDao(appDatabase: CardManagerDatabase): CardDao = appDatabase.cardDao()

    @Provides
    fun provideTransactionDao(appDatabase: CardManagerDatabase): TransactionDao = appDatabase.transactionDao()
}
