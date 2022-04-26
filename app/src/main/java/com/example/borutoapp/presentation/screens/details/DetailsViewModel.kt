package com.example.borutoapp.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.util.Constants.HERO_ID_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero = MutableStateFlow<Hero?>(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(HERO_ID_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let {
                useCases.getSelectedHeroUseCase(heroId = heroId)
            }

        }

    }

    private val _uiEvent = MutableSharedFlow<UiState>()
    val uiEvent: SharedFlow<UiState> = _uiEvent.asSharedFlow()

    private val _colorPalette = mutableStateOf<Map<String, String>>(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun setPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

    fun startUiEvent() {
        viewModelScope.launch {
            _uiEvent.emit(UiState.GenerateColorPalette)
        }
    }

}


sealed class UiState() {
    object GenerateColorPalette : UiState()
}