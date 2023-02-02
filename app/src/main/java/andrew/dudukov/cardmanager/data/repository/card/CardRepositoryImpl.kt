package andrew.dudukov.cardmanager.data.repository.card

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.dao.CardDao
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.remote.SwissquoteRestAPI
import andrew.dudukov.cardmanager.data.source.remote.model.CardResponse
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remoteDataSource: SwissquoteRestAPI,
    private val localDataSource: CardDao,
    private val networkMapper: Mapper<CardResponse, CardDB>,
) : CardRepository {

    override suspend fun fetchCards(): List<CardDB> {
        val cards = remoteDataSource.getCards()
        val dbCards = networkMapper.map(cards)
        localDataSource.insert(dbCards)
        return localDataSource.getAll()
    }
}