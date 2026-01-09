@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaDiscord
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaXTwitter
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.colors.shifted
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

val HoverBrightenStyle = CssStyle {
    val color = colorMode.toPalette().color
    base {
        Modifier.color(color.shifted(colorMode.opposite, 0.2f))
    }
    hover {
        Modifier.color(color)
    }
}

@Composable
private fun SocialBar() {
    SimpleGrid(
        numColumns(3, lg = 3),
        //numColumns(3, lg = 5),
        Modifier
            .margin(0.px, 12.px)
            .padding(2.cssRem)
            .gap(1.5.cssRem)
    ) {
        Link("https://github.com/FrozenBlock", HoverBrightenStyle.toModifier()) {
            FaGithub(size = IconSize.X2)
        }
        Link("https://discord.com/invite/frozenblock", HoverBrightenStyle.toModifier()) {
            FaDiscord(size = IconSize.X2)
        }
        Link("https://x.com/FB_Oasis", HoverBrightenStyle.toModifier()) {
            FaXTwitter(size = IconSize.X2)
        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
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
