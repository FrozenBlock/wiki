@file:Suppress("FunctionName")

package net.frozenblock.net.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import net.frozenblock.net.CircleButtonVariant
import net.frozenblock.net.UncoloredButtonVariant
import org.jetbrains.compose.web.css.em

@Composable
fun IconButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = { onClick() },
        Modifier.setVariable(ButtonVars.FontSize, 1.em), // Make button icon size relative to parent container font size
        variant = CircleButtonVariant.then(UncoloredButtonVariant),
    ) {
        content()
    }
}

@Composable
fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}