package hu.davidhalma.validator

class Validator(
    private val validatableObjects: MutableCollection<Any>,
    private val rules: MutableCollection<Rule>,
    private val exception: RuntimeException
) {

    fun validate() {
        for (validatableObject in validatableObjects) {
            for (rule in rules) {
                throwIfNotValid(validatableObject, rule)
            }
        }
    }

    private fun throwIfNotValid(validatableObjects: Any, rule: Rule) {
        if (!rule.isValid(validatableObjects)) {
            throw exception
        }
    }

    class builder {
        private val validatableObjects = mutableListOf<Any>()
        private val rules = mutableListOf<Rule>()
        private var exception: RuntimeException = RuntimeException()

        fun validatableObject(validatableObjects: Any): builder {
            this.validatableObjects.add(validatableObjects)
            return this
        }

        fun validatableObject(validatableObjects: Collection<Any>): builder {
            this.validatableObjects.addAll(validatableObjects)
            return this
        }

        fun exception(exception: RuntimeException): builder {
            this.exception = exception
            return this
        }

        fun rule(rule: Rule): builder {
            rules.add(rule)
            return this
        }

        fun rule(rules: Collection<Rule>): builder {
            this.rules.addAll(rules)
            return this
        }

        fun build(): Validator {
            if (this.validatableObjects.isEmpty() || this.rules.isEmpty()) {
                throw IllegalArgumentException("You must specify at least one validatable objects and rules.")
            }
            return Validator(this.validatableObjects, this.rules, this.exception)
        }
    }
}