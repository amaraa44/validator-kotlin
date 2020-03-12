package hu.davidhalma.validator

interface Rule {
    fun isValid(o: Any?): Boolean
}