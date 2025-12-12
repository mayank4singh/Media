package com.example.mediaupload.data.remote

import com.example.mediaupload.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import io.ktor.client.plugins.HttpTimeout
import io.github.jan.supabase.realtime.Realtime

object SupabaseClientProvider {

    @OptIn(SupabaseInternal::class)
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_ANON_KEY
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
        install(Realtime)

        httpConfig {
            install(HttpTimeout) {
                requestTimeoutMillis = 10000 // 10 seconds
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
        }
    }
}

