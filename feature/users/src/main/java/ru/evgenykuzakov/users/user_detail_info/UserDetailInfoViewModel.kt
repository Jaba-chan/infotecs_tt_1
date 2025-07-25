package ru.evgenykuzakov.users.user_detail_info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.evgenykuzakov.domain.use_case.GetUserDetailInfoUseCase
import javax.inject.Inject

@HiltViewModel
class UserDetailInfoViewModel @Inject constructor(
    private val getUserDetailInfoUseCase: GetUserDetailInfoUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailInfoUIState())
    val uiState: StateFlow<UserDetailInfoUIState> = _uiState

    private val userId: Long = savedStateHandle["userId"]!!

    init {
        viewModelScope.launch {
            getUserDetailInfoUseCase(userId = userId)
                .collectLatest { result ->
                    _uiState.update {
                        it.copy(
                            user = result
                        )
                    }
                }
        }
    }
}