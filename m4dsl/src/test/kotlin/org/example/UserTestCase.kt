package org.example

import org.junit.Test
import kotlin.test.assertEquals

class UserTestCase {
    @Test
    fun `test user`() {
        val user = buildUser {
            name {
                first = "Kirill"
                last = "Krylov"
            }
            contacts {
                email = "email@gmail.com"
                phone = "81234567890"
            }
            actions {
                add(Action.UPDATE)
                add(Action.ADD)

                +Action.DELETE
                +Action.READ
            }
            availability {
                monday("11:30")
                friday("18:00")
            }
        }

        assertEquals("Kirill", user.firstName)
    }
}
