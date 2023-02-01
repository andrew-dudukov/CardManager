package andrew.dudukov.cardmanager.data.repository.card

import andrew.dudukov.cardmanager.ui.model.card.Card

interface CardRepository {

    suspend fun fetchCards(): List<Card>
}