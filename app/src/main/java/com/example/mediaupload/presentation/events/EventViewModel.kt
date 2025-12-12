package com.example.mediaupload.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaupload.data.model.Event
import com.example.mediaupload.data.repository.EventRepository
import com.example.mediaupload.data.remote.SupabaseClientProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.auth

class EventViewModel(
    private val repo: EventRepository = EventRepository()
) : ViewModel() {

    private val client = SupabaseClientProvider.client

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    // ---------------------------
    // LOAD EVENTS
    // ---------------------------
    fun loadEvents(userId: String) {
        viewModelScope.launch {
            _loading.emit(true)
            _error.emit(null)

            try {
                val list = repo.getEvents(userId)
                _events.emit(list)
            } catch (e: Exception) {
                _error.emit(e.message)
            } finally {
                _loading.emit(false)
            }
        }
    }


    // ---------------------------
    // CREATE EVENT
    // ---------------------------
    fun createEvent(title: String, description: String, date: String) {
        viewModelScope.launch {
            _loading.emit(true)

            try {
                val session = client.auth.currentSessionOrNull()
                val userId = session?.user?.id ?: return@launch

                val event = Event(
                    id = null,
                    user_id = userId,
                    title = title,
                    description = description,
                    date = date
                )

                repo.createEvent(event)
                loadEvents(userId)

            } catch (e: Exception) {
                _error.emit(e.message)
            } finally {
                _loading.emit(false)
            }
        }
    }


    // ---------------------------
    // DELETE EVENT
    // ---------------------------
    fun deleteEvent(id: Int) {
        viewModelScope.launch {
            try {
                repo.deleteEvent(id)
                val userId = client.auth.currentSessionOrNull()?.user?.id ?: return@launch
                loadEvents(userId)
            } catch (e: Exception) {
                _error.emit(e.message)
            }
        }
    }
}
