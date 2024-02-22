@file:Suppress("FunctionName", "unused")

package net.frozenblock.net.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textShadow
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.style.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.HeadlineTextStyle
import net.frozenblock.net.components.layouts.PageLayout
import net.frozenblock.net.components.sections.PageList
import net.frozenblock.net.components.sections.WikiPage
import net.frozenblock.net.components.widgets.SideMenuState
import net.frozenblock.net.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

private const val PAGE_URL = "wilder-wild"

@Page
@Composable
fun WilderWild() {
    PageLayout("Wilder Wild") {
        Row(HeroContainerStyle.toModifier(), horizontalArrangement = Arrangement.Center) {
            Box(contentAlignment = Alignment.Center) {
                val sitePalette = ColorMode.current.toSitePalette()
                Div(HeadlineTextStyle.toAttrs()) {
                    SpanText(
                        "Wilder Wild Wiki",
                        Modifier
                            .color(sitePalette.brand.primary)
                            // Use a shadow so this light-colored word is more visible in light mode
                            .textShadow(0.px, 0.px, blurRadius = 0.5.cssRem, color = Colors.Gray)
                            .textAlign(TextAlign.Center)
                    )
                }
            }
        }

        WWPageList()
    }
}

@Composable
private fun WWPageList() {
    val pages = remember {
        listOf(
            WikiPage("first wiki page", "/$PAGE_URL/first")
        )
    }

    var menuState: MutableState<SideMenuState> by remember { mutableStateOf(SideMenuState.CLOSED) }

    PageList(pages, menuState)
}