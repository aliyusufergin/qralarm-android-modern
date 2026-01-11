package com.sweak.qralarm.features.appearance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweak.qralarm.R
import com.sweak.qralarm.core.designsystem.icon.QRAlarmIcons
import com.sweak.qralarm.core.designsystem.theme.QRAlarmTheme

@Composable
fun AppearanceScreen(
    onBackClicked: () -> Unit
) {
    val viewModel = hiltViewModel<AppearanceViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    AppearanceScreenContent(
        state = state,
        onEvent = { event ->
            when (event) {
                is AppearanceScreenUserEvent.OnBackClicked -> onBackClicked()
                else -> viewModel.onEvent(event)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppearanceScreenContent(
    state: AppearanceScreenState,
    onEvent: (AppearanceScreenUserEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.appearance),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent(AppearanceScreenUserEvent.OnBackClicked) }
                    ) {
                        Icon(
                            imageVector = QRAlarmIcons.BackArrow,
                            contentDescription = stringResource(R.string.content_description_back_arrow_icon)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Theme Section Header
            Text(
                text = stringResource(R.string.theme),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Theme Options Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    ThemeOptionItem(
                        title = stringResource(R.string.theme_system),
                        subtitle = "Follow device settings",
                        isSelected = state.selectedThemeMode == ThemeMode.SYSTEM,
                        onClick = { onEvent(AppearanceScreenUserEvent.OnThemeModeSelected(ThemeMode.SYSTEM)) }
                    )

                    ThemeOptionItem(
                        title = stringResource(R.string.theme_light),
                        subtitle = "Always light appearance",
                        isSelected = state.selectedThemeMode == ThemeMode.LIGHT,
                        onClick = { onEvent(AppearanceScreenUserEvent.OnThemeModeSelected(ThemeMode.LIGHT)) }
                    )

                    ThemeOptionItem(
                        title = stringResource(R.string.theme_dark),
                        subtitle = "Always dark appearance",
                        isSelected = state.selectedThemeMode == ThemeMode.DARK,
                        onClick = { onEvent(AppearanceScreenUserEvent.OnThemeModeSelected(ThemeMode.DARK)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeOptionItem(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.surfaceVariant
            )
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun AppearanceScreenContentLightPreview() {
    QRAlarmTheme(darkTheme = false) {
        AppearanceScreenContent(
            state = AppearanceScreenState(),
            onEvent = {}
        )
    }
}

@Preview
@Composable
private fun AppearanceScreenContentDarkPreview() {
    QRAlarmTheme(darkTheme = true) {
        AppearanceScreenContent(
            state = AppearanceScreenState(selectedThemeMode = ThemeMode.DARK),
            onEvent = {}
        )
    }
}
