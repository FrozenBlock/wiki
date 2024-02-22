@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import net.frozenblock.net.CircleButtonVariant
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Span

val FooterStyle by ComponentStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

private class SocialButton(val link: String, val icon: String)

@Composable
private fun SocialButtonItem(social: SocialButton) {
    Link(social.link) {
        Button(onClick = {}, variant = CircleButtonVariant, colorScheme = ColorSchemes.Monochrome) {
            Image(social.icon, height = 32)
        }
    }
}

@Composable
private fun SocialBar() {
    val socials = remember {
        listOf(
            SocialButton("https://github.com/FrozenBlock", "/social/github.svg")
        )
    }

    SimpleGrid(numColumns(1, md = 5)) {
        socials.forEach { social -> SocialButtonItem(social) }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Column {
            SocialBar()

            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                val sitePalette = ColorMode.current.toSitePalette()
                SpanText("FrozenBlock is not approved by nor affiliated with ")
                Link(
                    "https://www.minecraft.net/en-us",
                    "Mojang Studios",
                    Modifier.setVariable(ColorVar, sitePalette.brand.accent)
                        .fontWeight(FontWeight.Bold),
                    variant = UncoloredLinkVariant
                )
                SpanText(".")
            }
        }
    }
}
