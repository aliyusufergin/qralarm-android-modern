package com.sweak.qralarm.core.designsystem.theme

import androidx.compose.ui.graphics.Color

// ============================================
// QRAlarm Clean Theme
// Light = White, Dark = Black
// Accent = Violet
// ============================================

// Legacy brand colors (kept for compatibility)
val Jacarta = Color(0xFF38285C)
val BlueZodiac = Color(0xFF0C1445)
val ButterflyBush = Color(0xFF5C54A4)
val Monza = Color(0xFFB00020)
val Nobel = Color(0xFFB7B7B7)

// Accent color (used for buttons, FAB, highlights)
val AccentViolet = Color(0xFF8B5CF6)
val AccentVioletLight = Color(0xFFA78BFA)

// ============================================
// LIGHT THEME - Pure White
// ============================================
val md_theme_light_primary = Color.White                 // Pure white
val md_theme_light_onPrimary = Color(0xFF1F2937)         // Dark gray text
val md_theme_light_primaryContainer = Color(0xFFF3F4F6)
val md_theme_light_onPrimaryContainer = Color(0xFF1F2937)
val md_theme_light_secondary = Color(0xFFF9FAFB)         // Very light gray
val md_theme_light_onSecondary = Color(0xFF374151)
val md_theme_light_secondaryContainer = Color(0xFFE5E7EB)
val md_theme_light_onSecondaryContainer = Color(0xFF1F2937)
val md_theme_light_tertiary = AccentViolet               // Violet accent
val md_theme_light_onTertiary = Color.White
val md_theme_light_tertiaryContainer = Color(0xFFEDE9FE)
val md_theme_light_onTertiaryContainer = Color(0xFF4C1D95)
val md_theme_light_error = Color(0xFFDC2626)
val md_theme_light_onError = Color.White
val md_theme_light_errorContainer = Color(0xFFFEE2E2)
val md_theme_light_onErrorContainer = Color(0xFF7F1D1D)
val md_theme_light_background = Color.White
val md_theme_light_onBackground = Color(0xFF1F2937)
val md_theme_light_surface = Color.White
val md_theme_light_onSurface = Color(0xFF1F2937)
val md_theme_light_surfaceVariant = Color(0xFFD1D5DB)    // Toggle track - visible
val md_theme_light_onSurfaceVariant = Color(0xFF374151)  // Placeholder - dark gray
val md_theme_light_outline = Color(0xFF9CA3AF)           // Visible border
val md_theme_light_outlineVariant = Color(0xFFD1D5DB)
val md_theme_light_inverseSurface = Color(0xFF1F2937)
val md_theme_light_inverseOnSurface = Color(0xFFF9FAFB)
val md_theme_light_inversePrimary = AccentVioletLight

// ============================================
// DARK THEME - Pure Black (OLED)
// No blue tint - pure grays only
// ============================================
val md_theme_dark_primary = Color.Black                  // Pure black
val md_theme_dark_onPrimary = Color(0xFFF5F5F5)          // Light text
val md_theme_dark_primaryContainer = Color(0xFF1A1A1A)   // Pure dark gray
val md_theme_dark_onPrimaryContainer = Color(0xFFF5F5F5)
val md_theme_dark_secondary = Color.Black                // Also black (no gradient)
val md_theme_dark_onSecondary = Color(0xFFE5E5E5)
val md_theme_dark_secondaryContainer = Color(0xFF262626)
val md_theme_dark_onSecondaryContainer = Color(0xFFD4D4D4)
val md_theme_dark_tertiary = AccentVioletLight           // Violet accent
val md_theme_dark_onTertiary = Color(0xFF2E1065)
val md_theme_dark_tertiaryContainer = Color(0xFF4C1D95)
val md_theme_dark_onTertiaryContainer = Color(0xFFEDE9FE)
val md_theme_dark_error = Color(0xFFFCA5A5)
val md_theme_dark_onError = Color(0xFF7F1D1D)
val md_theme_dark_errorContainer = Color(0xFF991B1B)
val md_theme_dark_onErrorContainer = Color(0xFFFEE2E2)
val md_theme_dark_background = Color.Black               // Pure black
val md_theme_dark_onBackground = Color(0xFFF5F5F5)
val md_theme_dark_surface = Color(0xFF0A0A0A)            // Almost black
val md_theme_dark_onSurface = Color(0xFFF5F5F5)
val md_theme_dark_surfaceVariant = Color(0xFF4A4A4A)     // Toggle track - visible gray
val md_theme_dark_onSurfaceVariant = Color.White         // Placeholder text - pure white
val md_theme_dark_outline = Color(0xFF808080)            // Mid gray borders  
val md_theme_dark_outlineVariant = Color(0xFF666666)     // Dividers
val md_theme_dark_inverseSurface = Color(0xFFF5F5F5)
val md_theme_dark_inverseOnSurface = Color(0xFF1F2937)
val md_theme_dark_inversePrimary = AccentViolet