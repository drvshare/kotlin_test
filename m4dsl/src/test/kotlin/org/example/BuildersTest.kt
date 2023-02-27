package org.example

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
            .withDrink(JDrink.COFFEE)
            .build()

        assertTrue(breakfast.bacon)
        assertEquals(JDrink.COFFEE, breakfast.drink)
    }

    @Test
    fun `kotlin breakfast builder test`() {
        val breakfast = breakfast {
            eggs = 3
            bacon = true
            title = "Simple"
            drink = KDrink.COFFEE
        }

        assertTrue(breakfast.bacon)
        assertEquals(KDrink.COFFEE, breakfast.drink)
    }
}
