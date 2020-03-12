package hu.davidhalma.validator

import hu.davidhalma.validator.Validator.builder
import hu.davidhalma.validator.rules.GreaterThanZeroRule
import hu.davidhalma.validator.rules.NotEmptyStringRule
import hu.davidhalma.validator.rules.ObjectNotNullRule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidatorTest {

    companion object {
        private const val EXCEPTION_MESSAGE = "Bad param!"
    }

    @Test
    fun `Test validate integer doesNotThrowException`() {
        assertDoesNotThrow {
            builder()
                .validatableObject(1)
                .validatableObject(4)
                .validatableObject(listOf(3, 4, 6, 8, 12))
                .rule(ObjectNotNullRule())
                .rule(GreaterThanZeroRule())
                .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                .build().validate()
        }
    }

    @Test
    fun `Test validate empty objects throws exception`() {
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .rule(ObjectNotNullRule())
                    .rule(GreaterThanZeroRule())
                    .build().validate()
            }
        assertEquals("You must specify at least one validatable objects and rules.", exception.message)
    }

    @Test
    fun test_validate_integer_throwsException() {
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .validatableObject(1)
                    .validatableObject(4)
                    .validatableObject(listOf(3, 4, 6, 8, 0))
                    .rule(listOf(ObjectNotNullRule(), GreaterThanZeroRule()))
                    .rule(ObjectNotNullRule())
                    .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                    .build().validate()
            }
        assertEquals(EXCEPTION_MESSAGE, exception.message)
    }

    @Test
    fun test_validate_integer_throwsException1() {
        val integers: MutableList<Any> = mutableListOf(432, 54, 877, 12355, -1)
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .validatableObject(1)
                    .validatableObject(4)
                    .validatableObject(integers)
                    .rule(ObjectNotNullRule())
                    .rule(GreaterThanZeroRule())
                    .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                    .build().validate()
            }
        assertEquals(EXCEPTION_MESSAGE, exception.message)
    }

    @Test
    fun test_validate_object_throwsException() {
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .validatableObject(listOf("", null))
                    .rule(NotEmptyStringRule())
                    .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                    .build().validate()
            }
        assertEquals(EXCEPTION_MESSAGE, exception.message)
    }

    @Test
    fun test_validate_object_doesNotThrowException() {
        assertDoesNotThrow {
            builder()
                .validatableObject(listOf("asdf", " fjdsl"))
                .rule(NotEmptyStringRule())
                .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                .build().validate()
        }
    }

    @Test
    fun test_validate_object_throwsException1() {
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .validatableObject("123")
                    .rule(NotEmptyStringRule())
                    .rule(GreaterThanZeroRule())
                    .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                    .build().validate()
            }
        assertEquals(EXCEPTION_MESSAGE, exception.message)
    }

    @Test
    fun test_validate_object_throwsException2() {
        val exception: IllegalArgumentException =
            assertThrows(IllegalArgumentException::class.java) {
                builder()
                    .validatableObject(listOf("asd", null))
                    .rule(NotEmptyStringRule())
                    .exception(IllegalArgumentException(EXCEPTION_MESSAGE))
                    .build().validate()
            }
        assertEquals(EXCEPTION_MESSAGE, exception.message)
    }
}