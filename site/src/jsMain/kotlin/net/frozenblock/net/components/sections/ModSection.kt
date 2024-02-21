package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.components.style.boxShadow
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px

fun Modifier.background(colorMode: ColorMode) =
    this.then(when (colorMode) {
        ColorMode.DARK -> Modifier.backgroundImage(
            radialGradient(
                RadialGradient.Shape.Circle,
                Color.Companion.rgb(41, 41, 46),
                Color.rgb(25, 25, 28),
                CSSPosition.Top
            )
        )
        ColorMode.LIGHT -> Modifier.backgroundColor(Colors.White)
    })

private class Mod(val link: String, val name: String, val icon: String)

val ModItemStyle by ComponentStyle.base {
    Modifier.margin(18.px)
}

@Composable
private fun ModItem(mod: Mod) {
    val colorMode by ColorMode.currentState

    Box(
        ModItemStyle.toModifier().then(
            Modifier
                .borderRadius(12.px)
                .background(colorMode)
                .padding(2.em)
                .boxShadow(colorMode)
        )
    ) {
        Column(modifier = Modifier.textAlign(TextAlign.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            SpanText(mod.name, Modifier.fontWeight(FontWeight.Bold).margin(bottom = 0.75.cssRem))
            Image(mod.icon, width = 192, height = 192)
        }
    }
}

@Composable
fun ModSection() {
    val mods = remember {
        listOf(
            Mod("/wilder-wild", "Wilder Wild", "/wilder_wild.png"),
            Mod("/configurable-everything", "Configurable Everything", "/configurable_everything.png"),
            Mod("/geometry-dash", "Geometry Dash", "/geometry_dash.png"),
            Mod("/frozenlib", "FrozenLib", "/frozenlib.png")
        )
    }

    SimpleGrid(numColumns(1, md = 3)) {
        mods.forEach { mod -> ModItem(mod) }
    }
}