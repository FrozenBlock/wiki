@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.components.style.background
import net.frozenblock.net.components.style.boxShadow
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px

private class Mod(val link: String, val name: String, val icon: String)

val ModItemStyle by ComponentStyle.base {
    Modifier.margin(18.px)
}

@Composable
private fun ModItem(mod: Mod) {
    val colorMode by ColorMode.currentState
    val sitePalette = ColorMode.current.toSitePalette()

    Link(mod.link,
        ModItemStyle.toModifier().then(
            Modifier
                .color(sitePalette.brand.primary)
                .textShadow(0.px, 0.px, blurRadius = 0.15.cssRem, color = Colors.Gray)
        ),
        variant = UncoloredLinkVariant
    ) {
        Box(
            Modifier.then(
                Modifier
                    .borderRadius(12.px)
                    .padding(2.em)
                    .background(colorMode)
                    .boxShadow(colorMode)
            )
        ) {
            Column(
                modifier = Modifier.textAlign(TextAlign.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SpanText(mod.name, Modifier.fontWeight(FontWeight.Bold).margin(bottom = 0.75.cssRem).whiteSpace(WhiteSpace.NoWrap))
                Image(mod.icon, width = 192, height = 192)
            }
        }
    }
}

@Composable
fun ModSection() {
    val mods = remember {
        listOf(
            Mod("/wilder-wild", "Wilder Wild", "/mod/wilder_wild.png"),
            Mod("/configurable-everything", "Configurable Everything", "/mod/configurable_everything.png"),
            Mod("/geometry-dash", "Geometry Dash", "/mod/geometry_dash.png"),
            Mod("/frozenlib", "FrozenLib", "/mod/frozenlib.png")
        )
    }

    SimpleGrid(numColumns(1, md = 3)) {
        mods.forEach { mod -> ModItem(mod) }
    }
}