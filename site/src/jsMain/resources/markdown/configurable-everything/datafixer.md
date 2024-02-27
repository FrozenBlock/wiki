---
root: .components.layouts.WikiLayout("DataFixer Config")
title: DataFixer Config
---

## About
This config is a port of the mod [Configurable Data Fixers](https://modrinth.com/mod/configurable-data-fixers)

This config gives the ability to convert registered IDs.\
For context, each block, entity, item, biome, dimension, etc. are each registered with a unique ID. (Ex: minecraft:diamond_pickaxe)

This config works with vanilla registries as well as modded registries added through Fabric's Registry API.
Can be useful for modpacks as mods are often changed.

A more advanced "schema" format is available with the types `block`, `biome`, `entity`, and `item`, however it is recommended to use registry fixers for items. Using the item schema fixer can result in items being removed from containers.


## Schemas
The schemas will run based on data version. Schemas with lower versions will run first, and those with higher versions will run last. If the main data version (the “dataVersion” option at the top of the config) is lower than the schema’s data version, the schema will not run.

When the datafixers are used in game, the main data version will save per-world. It will save to chunks, entities, items, world data, and more things. Each saved data version is read in order for the game to tell if it should bother using the datafixer. If the main data version is higher than the saved version, schemas that also have higher version numbers will run.

## Config file
Here is the config format:
```
{
  "dataVersion": 0,
  "schemas": [
    {
      "version": 1,
      "fixers": [
        {
          "type": "biome",
          "fixers": [
            {
              "old_id": "examplemod:example_biome",
              "new_id": "newmod:new_biome"
            }
          ]
        }
      ]
    }
  ],
  "registryFixers": [
    {
      "registry_key": "minecraft:block",
      "fixers": [
        {
          "old_id": "examplemod:example_block",
          "new_id": "newmod:new_block"
        }
      ]
    },
    {
      "registry_key": "minecraft:item",
      "fixers": [
        {
          "old_id": "examplemod:example_item",
          "new_id": "newmod:new_item"
        }
      ]
    }
  ]
}

```

The config file can be found at `./config/configurable_everything/datafixer.json5`
The config file will have examples set up for you.

Note: Entries in the config will only convert if the `old_id` is not found in the game. Ex: If you have `old_id` set to `minecraft:grass_block` and `new_id` set to `minecraft:stone`, grass blocks will stay the same.