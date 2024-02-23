@file:Suppress("FunctionName", "unused")

package net.frozenblock.net.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import net.frozenblock.net.components.sections.WikiPage

private const val PAGE_URL = "wilder-wild"

@Suppress("NOTHING_TO_INLINE")
private inline fun page(path: String): String = "/$PAGE_URL/$path"

@Composable
fun wilderwild(): List<WikiPage> {
    val pages = remember {
        listOf(
            WikiPage("Home", "/$PAGE_URL"),
            WikiPage("first wiki page", page("first"))
        )
    }
    return pages
}