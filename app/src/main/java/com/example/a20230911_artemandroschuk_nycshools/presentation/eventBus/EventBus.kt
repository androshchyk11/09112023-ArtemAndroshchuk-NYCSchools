package com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus

import com.example.a20230911_artemandroschuk_nycshools.presentation.eventBus.events.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBus @Inject constructor() {
    companion object {
        private val _events = MutableSharedFlow<Event>()
        val events = _events.asSharedFlow()

        suspend fun emitEvent(event: Event) {
            _events.emit(event)
        }
    }
}