package hu.davidhalma.validator.rules

import hu.davidhalma.validator.Rule

class NotEmptyStringRule : Rule {
    override fun isValid(o: Any?): Boolean {
        return if (o is String) {
            0 < o.length
        } else false
    }
}