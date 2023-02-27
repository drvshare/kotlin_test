@file:Suppress("PackageDirectoryMismatch")
package org.example.builders.java

enum class Drink {
    WATER,
    COFFEE,
    TEA
}

abstract class Meal {
    data class Breakfast(
        val eggs: Int,
        val bacon: Boolean,
        val drink: Drink,
        val title: String
    ) : Meal()
}

