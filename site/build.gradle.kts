import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "net.frozenblock.net"
version = "1.0-SNAPSHOT"

class WikiPageEntry(val fileName: String, val route: String, val title: String)

kobweb {
    app {
        index {
            description.set("Wiki for FrozenBlock's mods")
        }
    }

    /*markdown {
        process.set { markdownEntries ->
            val pages: MutableMap<String, MutableList<WikiPageEntry>> = mutableMapOf()

            val requiredFields = listOf("title")
            for (entry in markdownEntries) {
                val fm = entry.frontMatter
                val title = requiredFields
                    .map { key -> fm[key]?.singleOrNull() }
                    .takeIf { values -> values.all { it != null } }
                    ?.requireNoNulls()
                    ?: continue

                val path = entry.filePath
                val mod = path.substringBefore('/')
                val fileName = path.substringAfter('/')

                pages.getOrPut(mod) { mutableListOf() }.add(WikiPageEntry(fileName, entry.route, title))
            }

            val package = "net.frozenblock.net.gen"
            val path = "${package.replace('.', '/')}/WikiEntries.kt"
            generateKotlin(path, buildString {
                appendLine(
                    """
                    // This file is generated. Modify the build script if you need to change it.

                    package $package

                    import androidx.compose.runtime.*

                    class WikiPageEntry(val fileName: String, val route: String, val title: String)

                    val WIKI_PAGES: Map<String, List<WikiPageEntry>> = remember {
                        mapOf(
                    """.trimIndent()
                )

                // TODO: add the list of pages
                for ((mod, entries) in pages) {
                    appendLine("$mod to listOf(),")
                }

                appendLine(
                    """
                        )
                    }
                    """.trimIndent()
                )
            })
        }
    }*/
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("wiki.frozenblock.net" /*, includeServer = true*/)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                // This default template uses built-in SVG icons, but what's available is limited.
                // Uncomment the following if you want access to a large set of font-awesome icons:
                // implementation(libs.silk.icons.fa)
                implementation(libs.kobwebx.markdown)
            }
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        val jvmMain by getting {
//            dependencies {
//                compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//            }
//        }
    }
}
