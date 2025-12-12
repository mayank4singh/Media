package com.example.mediaupload.data.repository

import com.example.mediaupload.data.model.Event
import com.example.mediaupload.data.remote.SupabaseClientProvider
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository {

    private val client = SupabaseClientProvider.client

    suspend fun createEvent(event: Event) = withContext(Dispatchers.IO) {
        client.from("events").insert(event)
    }

    suspend fun getEvents(userId: String): List<Event> = withContext(Dispatchers.IO) {
        // FIX: Use the lambda syntax with a filter block
        client.from("events")
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<Event>()
    }

    suspend fun deleteEvent(id: Int) = withContext(Dispatchers.IO) {
        // FIX: Use the lambda syntax with a filter block
        client.from("events")
            .delete {
                filter {
                    eq("id", id)
                }
            }
    }
}
