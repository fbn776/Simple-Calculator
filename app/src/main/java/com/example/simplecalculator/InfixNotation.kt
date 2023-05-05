package com.example.simplecalculator

/**
 * @property[result] This property contains the infix-ed notation as a [MutableList<String>]
 * @property[hasDecimalError] Is true if the Queue passed has decimal errors
 * @property[hasOperatorError] Is true if the Queue passed has operator errors
 * @property[hasInvalidCharError] Is true if the queue has invalid characters
 */
interface InfixReturnType {
    val result: MutableList<String>
    var hasDecimalError: Boolean
    var hasOperatorError: Boolean
    var hasInvalidCharError: Boolean
}
/**
 * A queue to infix notation converter.
 * @property[operators] A string that contains the operators
 * @property[convert] Function to convert the queue to infix object. This returns an object that extends [InfixReturnType]
 */
class InfixNotation() {
    val operators = "+-÷×*/"

    fun convert(ls: Queue<Char>): InfixReturnType {

        val result = mutableListOf<String>("")
        //For checking if there's decimal error. That there's two or more decimal point in a number.
        var hasDecimalError = false
        var hasOperatorError = false
        var hasInvalidCharError = false
        var lastChar: Char = ls.first()
        while (!ls.isEmpty()) {
            //Get the first element of the queue
            val i = ls.pop()

            //If there's a lonely decimal point, raise error.
            if (i == '.' && lastChar !in "0123456789") {
                hasDecimalError = true
                break
            }

            //If the current character is number or decimal point, add it to the last element of `result`;
            if (i in "0123456789" || i == '.') {
                if (i == '.' && result.lastElm().last() == '.') {
                    hasDecimalError = true
                    break
                }

                //Concat the last string element of result with `i`;
                result.set(result.size - 1, result.lastElm() + i.toString())

            } else if (i in operators) {
                //If its an operator, then add it to the result as a new string item;
                result.add(i.toString())
                //Then add a new empty string for the next queue elements;
                result.add("")
            } else {
                //Invalid character error;
                hasInvalidCharError = true
                break
            }

            lastChar = i
        }

        //If there's an empty string in the result, then it means operator error;
        if ("" in result) {
            hasOperatorError = true
        }

        return object: InfixReturnType {
            override val result: MutableList<String>
                get() = result
            override var hasDecimalError: Boolean
                get() = hasDecimalError
                set(value) {}
            override var hasOperatorError: Boolean
                get() = hasOperatorError
                set(value) {}
            override var hasInvalidCharError: Boolean
                get() = hasInvalidCharError
                set(value) {}

        }
    }
}