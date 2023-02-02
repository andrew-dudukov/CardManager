package andrew.dudukov.cardmanager.di.dispatcher

import andrew.dudukov.cardmanager.core.coroutines.AppDispatchers
import andrew.dudukov.cardmanager.core.coroutines.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun bindDispatchers(): AppDispatchers = AppDispatchersImpl()
}
