package org.example.user.dsl

import org.example.user.models.Action
import org.example.user.models.User
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.util.*

@UserDsl
class NameCtx {
    var first = ""
    var second = ""
    var last = ""
}


@UserDsl
class ContactsCtx {
    var phone = ""
    var email = ""
}

@UserDsl
class ActionsCtx {
    private val _actions: MutableSet<Action> = mutableSetOf()

    val action: Set<Action>
        get() = _actions.toSet()

    fun add(action: Action) = _actions.add(action)

    fun add(value: String) = add(Action.valueOf(value = value))

    operator fun Action.unaryPlus() = add(this)

    operator fun String.unaryPlus() = add(this)
}

@UserDsl
class AvailabilityCtx {
    private val _availabilities: MutableSet<LocalDateTime> = mutableSetOf()

    val availability: Set<LocalDateTime>
        get() = _availabilities.toSet()

    fun sundey(time: String) = dayTimeOfWeek(DayOfWeek.SUNDAY, time)
    fun monday(time: String) = dayTimeOfWeek(DayOfWeek.MONDAY, time)
    fun tuesday(time: String) = dayTimeOfWeek(DayOfWeek.TUESDAY, time)
    fun wednesday(time: String) = dayTimeOfWeek(DayOfWeek.WEDNESDAY, time)
    fun thursday(time: String) = dayTimeOfWeek(DayOfWeek.THURSDAY, time)
    fun friday(time: String) = dayTimeOfWeek(DayOfWeek.FRIDAY, time)
    fun saturday(time: String) = dayTimeOfWeek(DayOfWeek.SATURDAY, time)


    private fun dayTimeOfWeek(day: DayOfWeek, time: String) {
        val dDay = LocalDate.now().with(TemporalAdjusters.next(day))

        val dTime = time.split(":")
            .map { it.toInt() }
            .let { (hours, minutes) -> LocalTime.of(hours, minutes) }

        _availabilities.add(LocalDateTime.of(dDay, dTime))
    }
}

@UserDsl
class UserCtx {
    private var id = UUID.randomUUID().toString()

    private var firstName = ""
    private var secondName = ""
    private var lastName = ""

    private var phone = ""
    private var email = ""

    private var actions = emptySet<Action>()

    private var availability = emptySet<LocalDateTime>()

    @UserDsl
    fun name(block: NameCtx.() -> Unit) {
        val ctx = NameCtx().apply(block)

        firstName = ctx.first
        secondName = ctx.second
        lastName = ctx.last
    }

    @UserDsl
    fun contacts(block: ContactsCtx.() -> Unit) {
        val ctx = ContactsCtx().apply(block)

        phone = ctx.phone
        email = ctx.email
    }

    @UserDsl
    fun actions(block: ActionsCtx.() -> Unit) {
        val ctx = ActionsCtx().apply(block)

        actions = ctx.action
    }

    @UserDsl
    fun availability(block: AvailabilityCtx.() -> Unit) {
        val ctx = AvailabilityCtx().apply(block)

        availability = ctx.availability
    }

    fun build() = User(
        id = id,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
        phone = phone,
        email = email,
        actions = actions,
        available = availability
    )
}

@UserDslU
fun buildUser(block: UserCtx.() -> Unit) = UserCtx().apply(block).build()

@DslMarker
annotation class UserDsl

@DslMarker
annotation class UserDslU
