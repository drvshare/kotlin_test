package org.example

import java.math.RoundingMode

sealed class PizzaType {
    data class Cheese(val cheeseName: String) : PizzaType()
    data class Veggie(val vegetables: List<String>) : PizzaType()
}

enum class Size(private val value: Int) {
    LARGE(12), MED(8), SMALL(6);

    fun calculateArea(): Double {
        // Area of circle given diameter
        return (Math.PI / 4).toBigDecimal().setScale(2, RoundingMode.UP).toDouble() * value * value
    }
}

class Pizza(private val type: PizzaType, private val size: Size) {
    fun prepare() {
        // 3
        println("Prepared ${size.name} sized $type pizza of area ${size.calculateArea()}")
    }
}

data class Result<T>(val item: T)

interface ICanCook<T> {
    fun cook(item: T): Result<T>
}

class PizzaCookingTraitForNormals : ICanCook<Pizza> {
    override fun cook(item: Pizza): Result<Pizza> {
        println("Collecting ingredients for normals")
        item.prepare()
        return Result(item)
    }
}

class PizzaCookingTraitForVegans : ICanCook<Pizza> {
    override fun cook(item: Pizza): Result<Pizza> {
        println("Collecting ingredients for vegans")
        item.prepare()
        return Result(item)
    }
}

interface IPizzaPackaging {
    fun packing()
}

class PackingPizzaInCarton : IPizzaPackaging {
    override fun packing() {
        println("Packing pizza in a carton")
    }
}

/*
class Chef<T>(private val trait: CanCook<T>) : ICanCook<T> {
    override fun cook(item: T): Result<T> {
        return trait.cook(item)
    }
}
*/

class Chef<T>(private val trait: ICanCook<T>, private val pack: IPizzaPackaging) : ICanCook<T> by trait, IPizzaPackaging by pack

fun main() {
    val pizza = Pizza(PizzaType.Cheese("Mozzarella"), Size.LARGE)

    val chefN = Chef(trait = PizzaCookingTraitForNormals(), pack = PackingPizzaInCarton())
    chefN.cook(pizza)
    chefN.packing()

    println()
    val chefV = Chef(trait = PizzaCookingTraitForVegans(), pack = PackingPizzaInCarton())
    chefV.cook(pizza)
    chefV.packing()
}
