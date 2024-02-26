@file:Suppress("FunctionName", "unused")

package net.frozenblock.net.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import net.frozenblock.net.components.sections.WikiPage

private const val PAGE_URL = "frozenlib"

@Suppress("NOTHING_TO_INLINE")
private inline fun page(path: String): String = "/$PAGE_URL/$path"

@Composable
fun lib(): List<WikiPage> {
    val pages = remember {
        listOf(
            WikiPage("FrozenLib", "/$PAGE_URL"),
            WikiPage("Getting Started", page("getting-started"))
        )
    }
    return pages
}