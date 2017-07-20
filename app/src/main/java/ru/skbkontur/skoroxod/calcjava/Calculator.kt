package ru.skbkontur.skoroxod.calcjava

import com.github.kittinunf.result.Result
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success

/**
 * Created by skorokhodov on 18.07.2017.
 */
fun ParseTerms(term: String): Result<List<String>, Exception> {

    var trimmed = term.trim()

    val outputQueue = ArrayList<String>();
    val stack = ArrayList<Token>();

    while (trimmed.length > 0) {
        var numb = GetNumber(trimmed)
        if (!numb.isNullOrEmpty()) {
            outputQueue.add(numb!!)
            trimmed = trimmed.removePrefix(numb!!).trim();
            continue
        }
        var token = GetToken(trimmed)
        if (token != null) {
            when {
                token == Token.COMMA -> {
                    val result = stack.popUntilElement(Token.BRACKET_OPEN)
                    result.fold(
                            success = { outputQueue.addAll(it.map { it.symbol }) },
                            failure = { return Result.error(Exception("В выражении нарушен баланс скобок или пропущена запятая в аргументах функции")) }
                    )
                }
                token == Token.BRACKET_OPEN -> {
                    stack.push(token)
                }
                token == Token.BRACKET_CLOSE -> {
                    val result = stack.popUntilElement(Token.BRACKET_OPEN)
                    result.fold(
                            success = {
                                outputQueue.addAll(it.map { it.symbol })
                                stack.pop() // выкинули скобку
                                val nextItem = stack.peek()
                                nextItem?.let {
                                    if (nextItem.isFunction) {
                                        outputQueue.add(stack.pop()!!.symbol)
                                    }
                                }
                            },
                            failure = { return Result.error(Exception("В выражении нарушен баланс скобок"))}
                    )
                }
                token.isFunction -> {
                    val op1 = token
                    while (OperatorCondition(op1, stack.peek())) {
                        outputQueue.add(stack.pop()!!.symbol)
                    }
                    stack.push(op1)
                }
                else -> {
                    return Result.error(Exception("Выражение содержит непонятные символы"))
                }
            }
            trimmed = trimmed.removePrefix(token.symbol).trim();
            continue
        }
        return Result.error( Exception("Выражение содержит непонятные символы"))
    }
    val tail = stack.popAll()
    if(tail.any{ it == Token.BRACKET_CLOSE || it == Token.BRACKET_OPEN} )
        return Result.error(Exception("В выражении нарушен баланс скобок"))
    outputQueue.addAll(tail.map { it.symbol })
    return Result.of { outputQueue }
}

fun OperatorCondition(operator1: Token, operator2: Token?): Boolean {
    if (operator2 == null) {
        return false
    }
    if (!operator2.isFunction) return false

    return operator1.isLeft && (operator1.priority <= operator2.priority) ||
            !operator1.isLeft && (operator1.priority < operator2.priority)
}


fun GetToken(term: String): Token? = Token.values().firstOrNull({ term.startsWith(it.symbol) })

fun GetNumber(term: String): String? {
    if (term.first() == '.') return null
    val number = term.takeWhile { it.isDigit() || it == '.' }
    return if (number.isNullOrEmpty()) null else number
}


enum class Token(val symbol: String,
                 val isFunction: Boolean = true,
                 val operandsCount: Short = 2,
                 val priority: Short = 10,
                 val isLeft: Boolean = true) {
    COMMA(",", false),
    BRACKET_OPEN("(", false),
    BRACKET_CLOSE(")", false),
    PLUS("+"),
    MINUS("-"),
    DIVISION("/", priority = 20),
    MULT("*", priority = 20),
    NEGATIVE("neg", operandsCount = 1, priority = 100),
    POWER("^", priority = 50),
    MAX("max",priority = 100),

}