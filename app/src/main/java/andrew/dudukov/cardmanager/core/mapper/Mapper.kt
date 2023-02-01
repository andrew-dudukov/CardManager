package andrew.dudukov.cardmanager.core.mapper

interface Mapper<I, O> {

    fun map(data: I): O

    fun map(data: List<I>): List<O> = data.map(::map)
}