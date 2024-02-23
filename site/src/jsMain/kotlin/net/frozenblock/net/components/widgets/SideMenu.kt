@file:Suppress("FunctionName")

package net.frozenblock.net.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.animation.Keyframes
import com.varabyte.kobweb.silk.components.animation.toAnimation
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.*

val SideMenuSlideInAnim by Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

@Composable
fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

enum class Side {
    LEFT,
    RIGHT
}

// Note: When the user closes the side menu, we don't immediately stop rendering it (at which point it would disappear
// abruptly). Instead, we start animating it out and only stop rendering it when the animation is complete.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN, CLOSING -> CLOSING
    }
}

@Composable
fun SideMenu(
    menuState: SideMenuState = SideMenuState.OPEN,
    side: Side = Side.RIGHT,
    size: Double = 1.0,
    closeable: Boolean = true,
    close: () -> Unit = {},
    onAnimationEnd: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp((8 * size).cssRem, 33.percent, (10 * size).cssRem))
                    .align(
                        when (side) {
                            Side.LEFT -> Alignment.CenterStart
                            Side.RIGHT -> Alignment.CenterEnd
                        }
                    )
                    // Close button will appear roughly over the hamburger button, so the user can close
                    // things without moving their finger / cursor much.
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(1.5.cssRem)
                    .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 2.cssRem)
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = when (side) {
                    Side.LEFT -> Alignment.Start
                    Side.RIGHT -> Alignment.End
                }
            ) {
                if (closeable) CloseButton(onClick = { close() })
                Column(
                    Modifier.padding(right = 0.5.cssRem)
                        .gap(1.5.cssRem)
                        .fontSize(1.2.cssRem),
                    horizontalAlignment = when (side) {
                        Side.RIGHT -> Alignment.End
                        Side.LEFT -> Alignment.Start
                    },
                    content = content
                )
            }
        }
    }
}