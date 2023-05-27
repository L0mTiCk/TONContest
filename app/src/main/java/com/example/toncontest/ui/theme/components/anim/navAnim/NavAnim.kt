package com.example.toncontest.ui.theme.components.anim.navAnim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.animation.fadeIn as fadeIn1

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavAnimationVertically(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { 1200 },
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn1(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavAnimationHorizontally(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { 1000 },
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn1(initialAlpha = 0.3f),
        exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}