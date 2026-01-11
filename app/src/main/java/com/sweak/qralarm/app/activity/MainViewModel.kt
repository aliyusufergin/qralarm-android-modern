package com.sweak.qralarm.app.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweak.qralarm.alarm.service.AlarmService
import com.sweak.qralarm.core.domain.alarm.AlarmsRepository
import com.sweak.qralarm.core.domain.alarm.RescheduleAlarms
import com.sweak.qralarm.core.domain.user.UserDataRepository
import com.sweak.qralarm.core.storage.datastore.QRAlarmPreferencesDataSource
import com.sweak.qralarm.features.emergency.settings.util.EMERGENCY_AVAILABLE_REQUIRED_MATCHES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val alarmsRepository: AlarmsRepository,
    private val rescheduleAlarms: RescheduleAlarms,
    private val preferencesDataSource: QRAlarmPreferencesDataSource
) : ViewModel() {

    private var _state = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    private val backendEventsChannel = Channel<MainActivityBackendEvent>()
    val backendEvents = backendEventsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            rescheduleAlarms()
            migrateToNewEmergencyRequiredMatches()

            _state.update { currentState ->
                currentState.copy(
                    shouldShowSplashScreen = false,
                    isIntroductionFinished = userDataRepository.isIntroductionFinished.first()
                )
            }
        }

        // Observe theme mode changes
        viewModelScope.launch {
            preferencesDataSource.getThemeMode().collect { themeMode ->
                _state.update { currentState ->
                    currentState.copy(themeMode = themeMode)
                }
            }
        }
    }

    private suspend fun migrateToNewEmergencyRequiredMatches() {
        val currentEmergencyRequiredMatches = userDataRepository.emergencyRequiredMatches.first()

        if (currentEmergencyRequiredMatches !in EMERGENCY_AVAILABLE_REQUIRED_MATCHES) {
            userDataRepository.setEmergencyRequiredMatches(
                matches = EMERGENCY_AVAILABLE_REQUIRED_MATCHES.last()
            )
        }
    }

    fun onEvent(event: MainActivityUserEvent) {
        when (event) {
            is MainActivityUserEvent.ObserveActiveAlarms -> viewModelScope.launch {
                alarmsRepository.getAllAlarms().collect { alarms ->
                    alarms.firstOrNull { alarm ->
                        alarm.isAlarmRunning || alarm.snoozeConfig.isAlarmSnoozed
                    }?.let { activeAlarm ->
                        if (activeAlarm.isAlarmRunning && !AlarmService.isRunning) {
                            alarmsRepository.setAlarmRunning(
                                alarmId = activeAlarm.alarmId,
                                running = false
                            )
                            return@let
                        }

                        backendEventsChannel.send(
                            MainActivityBackendEvent.NavigateToActiveAlarm(
                                alarmId = activeAlarm.alarmId
                            )
                        )
                    }
                }
            }
            is MainActivityUserEvent.OnAlarmSaved -> {
                // Alarm saved event handling - can be extended for other features
            }
        }
    }
}