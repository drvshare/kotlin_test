@file:Suppress("PackageDirectoryMismatch")
package org.example.builders.kotlin

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
    var eggs = 0
    var bacon = false
    var drink = Drink.WATER
    var title = ""

    fun build() = Meal.Breakfast(eggs, bacon, drink, title)
}

/*
fun breakfast(block: BreakfastBuilder.() -> Unit): Meal.Breakfast {
    val builder = BreakfastBuilder()
    builder.block()
    return builder.build()
}
*/

fun breakfast(block: BreakfastBuilder.() -> Unit) = BreakfastBuilder().apply(block).build()

fun main() {
    val myBreakfast = breakfast {
        eggs = 5
        bacon = true
        drink = Drink.COFFEE
        eggs =8
        eggs =8
        eggs =4
        title = "cool breakfast"
    }

    println(myBreakfast)
}
