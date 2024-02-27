@file:Suppress("FunctionName")

package net.frozenblock.net.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import net.frozenblock.net.gen.WikiPageEntry

@Composable
private fun PageItem(page: WikiPageEntry) {
    Link(
        page.route,
        page.title,
        modifier = Modifier.whiteSpace(WhiteSpace.NoWrap),
        variant = UncoloredLinkVariant
    )
}

@Composable
fun PageList(pages: List<WikiPageEntry>) {
    HorizontalDivider()
    pages.forEach { page -> PageItem(page) }
}