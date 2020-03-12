package hu.davidhalma.validator.rules

import hu.davidhalma.validator.Rule

class ObjectNotNullRule : Rule {
    override fun isValid(o: Any?): Boolean {
        return null != o
    }
}