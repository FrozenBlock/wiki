---
root: .components.layouts.WikiLayout("Scripting", net.frozenblock.net.pages.config_every())
---

{{{ net.frozenblock.net.components.widgets.WikiTitle("Scripting") }}}

---

This page is not complete and is scheduled for update 1.1

# About
Scripting gives players the ability to modify the game via Kotlin code that is run when the game is initializing.

It is recommended to become familiar with [Kotlin](https://kotlinlang.org/docs) before proceeding with scripting.

WARNING: Kotlin scripts interacting with Minecraft or mods may break at any point. There is no way to prevent this. If a project updates and it removes something relied on by a script, that script will break.

# Setup
The first thing to do is to install [Fabric Kotlin Extensions](https://modrinth.com/mod/fabric-kotlin-extensions). This mod provides the required dependencies for scripting to function.

Once installed, the `scripting` toggle in the main config needs to be set to true.

After booting the game, there should be a new `scripts` folder in the Configurable Everything config folder, `config/configurable_everything`.

# Create your first script
- Create a file ending with `.cevt.kts` (Ex: `script.cevt.kts`). This is a Kotlin script file.
- Edit the file with any preferred text editor
- Add `log(“Hello world”)` to the file
- Save the file
- Boot the game and look for “Hello world” in the logs
- If it appears, congratulations, you made your first script.

***

# Remapping
Remapping converts the obfuscated Minecraft jar into its deobfuscated form.

Configurable Everything uses this to enable great control over the game with scripts. It allows for simply adding blocks, items, entities, and accessing any part of any mod.

Nothing can only be done with remapping. Remapping only renames the code to be more simple to use.


To enable remapping, enable `remapping` in the Scripting config. It will require a network connection as it must download the Intermediary and Official Mojang Mappings.

# Tutorials

## Add a Block

This is only possible in 1.1.

A block can be added by adding the following code to a script
```kotlin
Registry.register(BuiltInRegistries.BLOCK, ResourceLocation(“example:block”), Block(Properties.of()))
```
This will create a block with the default block properties. To change the block id, change `example:block` in the `ResourceLocation` to a different value. The new value will be what the block is called.

To use the properties of another block, replace `Properties.of()` with `Properties.ofFullCopy(Blocks.DEEPSLATE)`. Please note that `Blocks.DEEPSLATE` is a placeholder. Any block can be used.

## Add an Item

This is only possible in 1.1.

## Config modifications

This is only possible in 1.1.


***

# File annotations

This section is incomplete.

## Compiler Options

To add compiler options for when the script is compiled, add the following to the top of the script.
```kotlin
@file:CompilerOptions(
    “key”, “value”,
    “key2”, “value2”
)
```