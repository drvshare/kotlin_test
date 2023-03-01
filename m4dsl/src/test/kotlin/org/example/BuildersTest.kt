package org.example

import org.example.builders.java.BreakfastBuilder
import org.example.builders.kotlin.Drink
import org.example.builders.kotlin.breakfast
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BuildersTest {
    @Test
    fun `java breakfast builder test`() {
        val breakfast = BreakfastBuilder()
            .withEggs(3)
            .withBacon(true)
            .withTitle("Simple")
            .withDrink(org.example.builders.java.Drink.COFFEE)
            .build()

        assertTrue(breakfast.bacon)
        assertEquals(org.example.builders.java.Drink.COFFEE, breakfast.drink)
    }

    @Test
    fun `kotlin breakfast builder test`() {
        val breakfast = breakfast {
            eggs = 3
            bacon = true
            title = "Simple"
            drink = Drink.COFFEE
        }

        assertTrue(breakfast.bacon)
        assertEquals(Drink.COFFEE, breakfast.drink)
    }
}
