package andrew.dudukov.cardmanager.data.mapper.card

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.data.source.remote.model.CardResponse
import javax.inject.Inject
import kotlin.random.Random

class CardResponseMapper @Inject constructor() : Mapper<CardResponse, CardDB> {

    override fun map(data: CardResponse): CardDB =
        data.run { CardDB(id, type, numbers, (amount.toInt() - Random.nextInt(0, 100)).toString(), currency) }
}