package org.example

import org.example.user.dsl.buildUser
import org.example.user.models.Action
import org.junit.Test
import kotlin.test.assertEquals

class UserTestCase {
    @Test
    fun `test user`() {
        val user = buildUser {
            name {
                first = "Alexandr"
                last = "Lichacheff"
            }
            contacts {
                email = "email@gmail.com"
                phone = "81234567890"
            }
            actions {
                add(Action.UPDATE)
                add("ADD")

                +Action.DELETE
                +"READ"
            }
            availability {
                monday("11:30")
                friday("18:00")
            }
        }

        println(user)
        assertEquals("Alexandr", user.firstName)
    }
}
