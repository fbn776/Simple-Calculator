package com.example.simplecalculator


/**
 * A very rough infix notation solver. It doesn't respect the order of precedence. It solves from left to right. (Why?, because solving an infix respecting its operator presidency wasn't the aim.);
 * @property[solve] This function takes in [MutableList<String>] and returns the solved answer as a [Float].
 * Warning: This doesn't error check
 */
class SolveInfix {
    fun solve(infixNot: MutableList<String>): Float {
        val solved = infixNot.toMutableList()
        var i = 0
        while(i < solved.size) {
            val s = solved[i]
            //Check if the current item in the list is an operator;
            if(s in "+-÷×") {
                //If it's an operator, get the operands present at index before and after of the operator, as it's a binary operator
                val oprnd1 = solved[i-1].toFloat()
                val oprnd2 = solved[i+1].toFloat()
                // Calculate the value based on the operator;
                val result: Float = when(s) {
                    "+" -> oprnd1 + oprnd2
                    "-" -> oprnd1 - oprnd2
                    "*", "×" -> oprnd1 * oprnd2
                    "÷", "/" -> oprnd1/oprnd2
                    else -> 0.0F
                }
                //Now remove the 2 operand and operator and replace it with the evaluated answer.
                solved.removeAt(i-1)
                solved.removeAt(i)
                solved[i-1] = result.toString()
                //Go back 1 position;
                i--
            }
            i++
        }
        return solved[0].toFloat()
    }
}