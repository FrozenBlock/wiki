package net.frozenblock.net.components.style

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.silk.theme.colors.ColorMode

@Composable
fun Modifier.background(colorMode: ColorMode) =
    this.then(when (colorMode) {
        ColorMode.DARK -> Modifier.backgroundImage(
            radialGradient(
                RadialGradient.Shape.Circle,
                Color.rgb(41, 41, 46),
                Color.rgb(25, 25, 28),
                CSSPosition.Top
            )
        )
        ColorMode.LIGHT -> Modifier.backgroundColor(Colors.White)
    })