@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.layout.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.components.layout.breakpoint.displayUntil
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import net.frozenblock.net.components.style.boxShadow
import net.frozenblock.net.components.widgets.HamburgerButton
import net.frozenblock.net.components.widgets.IconButton
import net.frozenblock.net.components.widgets.SideMenu
import net.frozenblock.net.components.widgets.SideMenuState
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem

val NavHeaderStyle by ComponentStyle.base {
    Modifier.fillMaxWidth().padding(1.cssRem)
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant))
}

@Composable
private fun MenuItems() {
    NavLink("/", "Start")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = { colorMode = colorMode.opposite },) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
}

@Composable
fun NavHeader(hamburgerContent: @Composable () -> Unit) {
    val colorMode by ColorMode.currentState
    Row(NavHeaderStyle.toModifier().then(Modifier.boxShadow(colorMode, 2)), verticalAlignment = Alignment.CenterVertically) {
        Link("/") {
            // Block display overrides inline display of the <img> tag, so it calculates centering better
            Image("/fb_banner_transparent.png", "FrozenBlock Logo", Modifier.height(5.cssRem).display(DisplayStyle.Block))
        }

        Spacer()

        Row(
            Modifier
                .fontSize(1.9.cssRem)
                .gap(1.cssRem),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

            ColorModeButton()
            HamburgerButton(onClick = { menuState = SideMenuState.OPEN })

            if (menuState != SideMenuState.CLOSED) {
                SideMenu(
                    menuState,
                    size = 1.5,
                    close = { menuState = menuState.close() },
                    onAnimationEnd = { if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED }
                ) {
                    MenuItems()
                    hamburgerContent()
                }
            }
        }
    }
}
