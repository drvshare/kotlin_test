package org.example

import org.junit.Test
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

class ConstValue(
    private val value: Int
) : ReadWriteProperty<Any?, Int> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        TODO("Not yet implemented")
    }
}

class DelegateExample {
    val constVal by ConstValue(100501)
    val lazyVal by lazy {
        println("calculate...")
        42
    }
}

/** свойства, хранимые в ассоциативном списке */
class MappedState(
    val state: MutableMap<String, String>
) {
    val id by state
    var name by state
    var description by state
    override fun toString(): String {
        return "MappedState(state=$state, id='$id', name='$name', description='$description')"
    }
}

/** observable properties */
class User {
    var name: String by Delegates.observable("<no name>") { _, old, new ->
        println("$old -> $new")
    }
}

/** Делегирование другому свойству */
class MyClass {
    var newName: Int = 0

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}

/** Делегирование интерфейсов */
interface Messenger {
    fun send(message: String)
}

class InstantMessenger(private val programName: String) : Messenger {
    override fun send(message: String) {
        println("Message `$message` has been sent through $programName")
    }
}

class SmartPhone(val name: String, m: Messenger) : Messenger by m

/*------------------------------------------------------------------------------*/
internal class DelegationKtTest {
    @Test
    fun `Delegating a lazy function`() {
        val example = DelegateExample()

        println(example.constVal)
        assertEquals(example.constVal, 100501)

        println(example.lazyVal)
        assertEquals(example.lazyVal, 42)
    }

    @Test
    fun `Delegating properties from the map`() {

        println("\nсвойства, хранимые в ассоциативном списке\n")
        val mappedState = MappedState(
            mutableMapOf(
                "id" to "id_value",
                "name" to "name_value",
                "description" to "description_value"
            )
        )

        println(mappedState)
        assertEquals("description_value", mappedState.description)
    }

    @Test
    fun `Delegating to an observer`() {

        println("\nobservable properties\n")
        val user = User()
        user.name = "first"
        user.name = "second"
    }

    @Test
    fun `Delegating to another property`() {
        println("\nДелегирование другому свойству\n")
        val myClass = MyClass()
        // Уведомление: 'oldName: Int' устарело.
        // Используйте 'newName'
        myClass.oldName = 42
        println(myClass.newName) // 42
    }


}


