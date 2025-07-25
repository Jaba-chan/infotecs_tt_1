package ru.evgenykuzakov.users.show_users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.evgenykuzakov.domain.use_case.GetUsersUseCase
import ru.evgenykuzakov.domain.use_case.RefreshUsersUseCase
import javax.inject.Inject

@HiltViewModel
class ShowUsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShowScreenUIState())
    val uiState: StateFlow<ShowScreenUIState> = _uiState

    init {
        getUsers()
    }

    private fun getUsers(){
        viewModelScope.launch {
            getUsersUseCase()
                .collectLatest { result ->
                    _uiState.update {
                        it.copy(
                            users = result
                        )
                    }
                }
        }
    }

    fun refreshUsers(){
        viewModelScope.launch {
            refreshUsersUseCase().collectLatest {}
            getUsers()
        }

    }
}