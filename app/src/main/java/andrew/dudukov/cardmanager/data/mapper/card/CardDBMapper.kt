package andrew.dudukov.cardmanager.data.mapper.card

import andrew.dudukov.cardmanager.core.mapper.Mapper
import andrew.dudukov.cardmanager.data.source.local.model.CardDB
import andrew.dudukov.cardmanager.ui.model.card.Card
import javax.inject.Inject

class CardDBMapper @Inject constructor() : Mapper<CardDB, Card> {

    override fun map(data: CardDB): Card = data.run { Card(id, type, numbers, amount, currency) }
}