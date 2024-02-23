@file:Suppress("FunctionName", "unused")

package net.frozenblock.net.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import net.frozenblock.net.components.sections.WikiPage

private const val PAGE_URL = "configurable-everything"

@Suppress("NOTHING_TO_INLINE")
private inline fun page(path: String): String = "/$PAGE_URL/$path"

@Composable
fun config_every(): List<WikiPage> {
    val pages = remember {
        listOf(
            WikiPage("Home", "/$PAGE_URL"),
            WikiPage("DataFixer Config", page("datafixer")),
            WikiPage("Scripting", page("scripting"))
        )
    }
    return pages
}