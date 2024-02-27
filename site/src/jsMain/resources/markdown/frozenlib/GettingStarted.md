---
root: .components.layouts.WikiLayout("Getting Started")
title: Getting Started
---

# Add FrozenLib as a dependency

FrozenLib can be added as a dependency by using the Modrinth maven repository.  
The Modrinth maven can be accessed via the following code for a Gradle buildscript
```kotlin
maven("https://api.modrinth.com/maven") {
    name = "Modrinth"

    content {
        includeGroup("maven.modrinth")
    }
}
```

Adding the dependency will be as follows
```kotlin
modImplementation("maven.modrinth:frozenlib:VERSION")
```
The version must be a version tag for a released version of FrozenLib, such as `1.6.1-mc1.20.4`.

