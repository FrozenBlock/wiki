@file:Suppress("FunctionName", "unused")

package net.frozenblock.net.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.HeadlineTextStyle
import net.frozenblock.net.SubheadlineTextStyle
import net.frozenblock.net.components.layouts.PageLayout
import net.frozenblock.net.components.sections.ModSection
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

sealed interface HeroContainerKind : ComponentKind

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle<HeroContainerKind> {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

val TopHeroContainerVariant = HeroContainerStyle.addVariant {
    Breakpoint.MD { Modifier.margin { top(5.vh) } }
}

@Page
@Composable
fun HomePage() {
    PageLayout("Start") {
        Row(HeroContainerStyle.toModifier().padding(bottom = 1.cssRem)) {
            Box {
                val sitePalette = ColorMode.current.toSitePalette()

                Column(Modifier.gap(2.cssRem)) {
                    Div(HeadlineTextStyle.toAttrs()) {
                        SpanText(
                            "Welcome to the ", Modifier.color(
                                when (ColorMode.current) {
                                    ColorMode.LIGHT -> Colors.Black
                                    ColorMode.DARK -> Colors.White
                                }
                            )
                        )
                        SpanText(
                            "FrozenBlock Wiki",
                            Modifier
                                .color(sitePalette.brand.primary)
                                // Use a shadow so this light-colored word is more visible in light mode
                                .textShadow(0.px, 0.px, blurRadius = 0.5.cssRem, color = Colors.Gray)
                        )
                    }

                    Div(SubheadlineTextStyle.toAttrs()) {
                        Text("Select a mod below to start.")
                    }
                }
            }
        }

        ModSection()
    }
}
