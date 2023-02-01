package andrew.dudukov.cardmanager.data.repository.card

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.dao.CardDao
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.remote.SwissquoteRestAPI
import andrew.dudukov.cardmanager.data.source.remote.model.CardResponse
import andrew.dudukov.cardmanager.ui.model.card.Card
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remoteDataSource: SwissquoteRestAPI,
    private val localDataSource: CardDao,
    private val networkMapper: Mapper<CardResponse, CardDB>,
    private val dbMapper: Mapper<CardDB, Card>
) : CardRepository {

    override suspend fun fetchCards(): List<Card> {
        val cards = remoteDataSource.getCards()
        val dbCards = networkMapper.map(cards)
        localDataSource.insert(dbCards)
        return dbMapper.map(localDataSource.getAll())
    }
}