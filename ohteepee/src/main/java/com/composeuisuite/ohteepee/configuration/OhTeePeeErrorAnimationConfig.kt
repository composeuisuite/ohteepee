package com.composeuisuite.ohteepee.configuration

import androidx.compose.animation.core.AnimationSpec
import com.composeuisuite.ohteepee.OhTeePeeDefaults

sealed class OhTeePeeErrorAnimationConfig {
    /**
     * Represents the configuration for a shake animation on the OTP field.
     *
     * @property repeat The number of times the shake animation should repeat.
     * @property translationXRange The range of horizontal movement (in pixels) for the shake animation.
     * @property animationSpec The specification of the animation such as duration, easing, etc.
     */
    data class Shake(
        val repeat: Int = 10,
        val translationXRange: Float = 5f,
        val animationSpec: AnimationSpec<Float> = OhTeePeeDefaults.defaultShakeAnimationSpec,
    ) : OhTeePeeErrorAnimationConfig()
}
