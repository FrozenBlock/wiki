@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import net.frozenblock.net.components.widgets.Side
import net.frozenblock.net.components.widgets.SideMenu

class WikiPage(val title: String, val link: String)

@Composable
private fun PageItem(page: WikiPage) {
    Link(page.link, page.title, modifier = Modifier.whiteSpace(WhiteSpace.NoWrap), variant = UncoloredLinkVariant)
}

@Composable
fun PageList(pages: List<WikiPage>, menuState: MutableState<SideMenuState>) {
    Row(NavHeaderStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .fontSize(1.5.cssRem)
                .gap(1.cssRem),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HamburgerButton(onClick = { menuState.value = SideMenuState.OPEN} )

            if (menuState.value != SideMenuState.CLOSED) {
                SideMenu(
                    menuState.value,
                    side = Side.LEFT,
                    close = { menuState.value = menuState.value.close() },
                    onAnimationEnd = { if (menuState.value == SideMenuState.CLOSING) menuState.value = SideMenuState.CLOSED }
                ) {
                    pages.forEach { page -> PageItem(page) }
                }
            }
        }
    }
}