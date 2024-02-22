@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.style.toModifier
import net.frozenblock.net.components.widgets.HamburgerButton
import net.frozenblock.net.components.widgets.Side
import net.frozenblock.net.components.widgets.SideMenu
import net.frozenblock.net.components.widgets.SideMenuState
import org.jetbrains.compose.web.css.cssRem

class WikiPage(val title: String, val link: String)

@Composable
private fun PageItem(page: WikiPage) {
    Link(page.link, page.title, modifier = Modifier.whiteSpace(WhiteSpace.NoWrap), variant = UncoloredLinkVariant)
}

@Composable
fun PageList(pages: List<WikiPage>, menuState: () -> SideMenuState, onStateUpdate: (SideMenuState) -> Unit) {
    Row(NavHeaderStyle.toModifier(), verticalAlignment = Alignment.Top) {
        Row(
            Modifier
                .fontSize(1.5.cssRem)
                .gap(1.cssRem),
            verticalAlignment = Alignment.Top
        ) {
            HamburgerButton(onClick = { onStateUpdate(SideMenuState.OPEN) })

            if (menuState() != SideMenuState.CLOSED) {
                SideMenu(
                    menuState(),
                    side = Side.LEFT,
                    close = { onStateUpdate(menuState().close()) },
                    onAnimationEnd = { if (menuState() == SideMenuState.CLOSING) onStateUpdate(SideMenuState.CLOSED) }
                ) {
                    pages.forEach { page -> PageItem(page) }
                }
            }
        }
    }
}