---
root: .components.layouts.WikiLayout("Scripting", net.frozenblock.net.pages.config_every())
---

{{{ net.frozenblock.net.components.widgets.WikiTitle("Scripting") }}}

---

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

***

# Tutorials

## Add a Block

A block can be added by adding the following code to a script
```kotlin
Registry.register(BuiltInRegistries.BLOCK, ResourceLocation(“example:block”), Block(Properties.of()))
```
This will create a block with the default block properties. To change the block id, change `example:block` in the `ResourceLocation` to a different value. The new value will be what the block is called.

To use the properties of another block, replace `Properties.of()` with `Properties.ofFullCopy(Blocks.DEEPSLATE)`. Please note that `Blocks.DEEPSLATE` is a placeholder. Any block can be used.

## Add an Item

An item can be added by adding the following code to a script
```kotlin
Registry.register(BuiltInRegistries.ITEM, ResourceLocation("example:item"), Item(Item.Properties()))`
```

## Config access and mutating

Configurable Everything has its own config scripting API.

Config accessors and mutators can be found in `ConfigData`.  
Each config has its own `ConfigData` object, such as `ConfigData.MAIN` or `ConfigData.BIOME_PLACEMENT`  
However, the Mixins config does not have a `ConfigData` object as it cannot be modified and is useless to access. It is also important to note that `ConfigData.SPLASH_TEXT` should only be accessed in client scripts as an error will occur when accessing it on a server.

A `ConfigData` object has a field `config` of type `Config` (the FrozenLib config class), a function `get()` with type of the specific config (ex: `MainConfig`), and a function `modify((T) -> Unit)` (where `T` is a config class) of type `Unit`.

`get()` will give access to the values of the config, however any changes made to them will not be saved. Each config instance is generated upon each call to support non-saved config modifications.  
`modify((T) -> Unit)` will register a config modification to the specified config. The modification function will be run each time the config is accessed within scripts or Configurable Everything.

Here is an example usage of the config API:
```kotlin
ConfigData.MAIN.modify { config ->
   config.biome_placement = true
}
```

***

# File annotations

## Maven Dependencies
This section is experimental and may not function correctly.

To add a maven dependency for usage within the script, use the [@DependsOn](https://github.com/JetBrains/kotlin/blob/master/libraries/scripting/dependencies/src/kotlin/script/experimental/dependencies/annotations.kt) annotation at the top of the file.  
Here is an example
```kotlin
@file:DependsOn("net.example:awesome_lib:2.0")
```

To add a maven repository, use the [@Repository](https://github.com/JetBrains/kotlin/blob/master/libraries/scripting/dependencies/src/kotlin/script/experimental/dependencies/annotations.kt) annotation at the top of the file.  
Here is an example
```kotlin
@file:Repository("https://maven.fabricmc.net/")
```

## Importing from other scripts
This section is experimental and may not function correctly.  
Note: This currently does not work whilst remapping is enabled due to the way remapped scripts are handled.

To import values from another script, use the [@Import](https://github.com/FrozenBlock/Configurable-Everything/blob/master/src/main/java/net/frozenblock/configurableeverything/scripting/util/Annotations.kt) annotation at the top of the file with the files to import.  
Here is an example
```kotlin
@file:Import("script1.cevt.kts", "script2.cevt.kts", "script3.cevt.kts")
```

## Compiler Options

To add compiler options for when the script is compiled, add the following to the top of the script.
```kotlin
@file:CompilerOptions(
    “key”, “value”,
    “key2”, “value2”
)
```