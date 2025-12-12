package com.example.mediaupload.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaupload.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.auth
import com.example.mediaupload.data.remote.SupabaseClientProvider


class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading


    private val supabase = SupabaseClientProvider.client


    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null

            val result = authRepository.signUp(email, password)
            _success.value = result.isSuccess

            if (result.isFailure) {
                _errorMessage.value = result.exceptionOrNull()?.message
            }

            _loading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                supabase.auth.signOut()
                _success.emit(false)
            } catch (e: Exception) {
                _errorMessage.emit("Logout failed: ${e.message}")
            }
        }
    }



    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null

            val result = authRepository.login(email, password)
            _success.value = result.isSuccess

            if (result.isFailure) {
                _errorMessage.value = result.exceptionOrNull()?.message
            }

            _loading.value = false
        }
    }
}
