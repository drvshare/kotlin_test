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

class BreakfastBuilder {
    private var eggs = 0
    private var bacon = false
    private var drink = Drink.WATER
    private var title = ""

    fun withEggs(eggs: Int): BreakfastBuilder {
        this.eggs = eggs
        return this
    }

    fun withBacon(bacon: Boolean): BreakfastBuilder {
        this.bacon = bacon
        return this
    }

    fun withDrink(drink: Drink): BreakfastBuilder {
        this.drink = drink
        return this
    }

    fun withTitle(title: String): BreakfastBuilder {
        this.title = title
        return this
    }

    fun build() = Meal.Breakfast(eggs, bacon, drink, title)
}





fun main() {
    val breakfast = Meal.Breakfast(2, true, Drink.COFFEE, "breakfast")

    val betterBr = BreakfastBuilder()
        .withEggs(3)
        .withBacon(true)
        .withDrink(Drink.TEA)
        .withEggs(3)
        .withEggs(7)
        .withEggs(1)
        .withTitle("better breakfest")
        .build()

    println(breakfast)

    println(betterBr)
}

