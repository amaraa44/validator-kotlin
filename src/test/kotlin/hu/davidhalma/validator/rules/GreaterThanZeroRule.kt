package hu.davidhalma.validator.rules

import hu.davidhalma.validator.Rule

class GreaterThanZeroRule : Rule {
    override fun isValid(o: Any?): Boolean {
        if (o is Int) {
            return 0 < o
        }
        return false
    }
}