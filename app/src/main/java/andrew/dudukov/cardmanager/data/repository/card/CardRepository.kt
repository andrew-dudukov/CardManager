package andrew.dudukov.cardmanager.data.repository.card

import andrew.dudukov.cardmanager.data.source.local.model.CardDB

interface CardRepository {

    suspend fun fetchCards(): List<CardDB>
}