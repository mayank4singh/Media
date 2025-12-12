package com.example.mediaupload.presentation.events

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.auth.auth
import com.example.mediaupload.data.model.Event
import com.example.mediaupload.data.remote.SupabaseClientProvider




@Composable
fun EventListScreen(
    viewModel: EventViewModel,
    onCreateNewEvent: () -> Unit
) {

    // ... inside EventListScreen ...

// Provide initial values for all Flows
    val events by viewModel.events.collectAsState(initial = emptyList())
    val loading by viewModel.loading.collectAsState(initial = false)
    val error by viewModel.error.collectAsState(initial = null)

// ... rest of the code ...

    val supabase = SupabaseClientProvider.client

    val userId: String? = supabase.auth.currentSessionOrNull()?.user?.id

    LaunchedEffect(userId) {
        if (userId != null) {
            viewModel.loadEvents(userId)
        }
    }


    // Automatically load events for logged-in user
    LaunchedEffect(userId) {
        if (userId != null) {
            viewModel.loadEvents(userId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Your Events", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCreateNewEvent,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create New Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
            return@Column
        }

        if (error != null) {
            Text(error ?: "", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(events) { event ->
                EventCard(event)
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(event.title, style = MaterialTheme.typography.titleLarge)

            if (event.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(event.description, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text("Date: ${event.date}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
