package com.fintech15.loanadvisor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintech15.loanadvisor.data.AuthenticationRepository
import com.fintech15.loanadvisor.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class AuthUiState(
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val errorText: String = ""
)

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            authenticationRepository.loginWithEmailAndPassword(email, password).let { result ->
                if (result is Result.Success) {
                    _uiState.update {
                        it.copy(isLoading = false, isLoggedIn = true)
                    }
                } else if (result is Result.Error) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = false,
                            errorText = result.exception.message!!
                        )
                    }
                }
            }
        }
    }

    fun register(email: String, password: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            authenticationRepository.registerWithEmailAndPassword(email, password).let { result ->
                if (result is Result.Success) {
                    _uiState.update {
                        it.copy(isLoading = false, isLoggedIn = true)
                    }
                } else if (result is Result.Error) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = false,
                            errorText = result.exception.message!!
                        )
                    }
                }
            }
        }
    }
}