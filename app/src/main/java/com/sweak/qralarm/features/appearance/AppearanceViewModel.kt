package com.sweak.qralarm.features.appearance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweak.qralarm.core.storage.datastore.QRAlarmPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppearanceViewModel @Inject constructor(
    private val preferencesDataSource: QRAlarmPreferencesDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(AppearanceScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesDataSource.getThemeMode().collect { themeModeValue ->
                _state.update { currentState ->
                    currentState.copy(selectedThemeMode = ThemeMode.fromValue(themeModeValue))
                }
            }
        }
    }

    fun onEvent(event: AppearanceScreenUserEvent) {
        when (event) {
            is AppearanceScreenUserEvent.OnThemeModeSelected -> {
                viewModelScope.launch {
                    preferencesDataSource.setThemeMode(event.themeMode.value)
                }
            }
            else -> { /* Handled by screen */ }
        }
    }
}
