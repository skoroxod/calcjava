package ru.skbkontur.skoroxod.calcjava

/**
 * Created by skorokhodov on 18.07.2017.
 */
fun Calculate(term: String): Pair<Boolean, String> {
    return Pair(true, term + "100500")
}


fun GetToken(term: String): String {
    var xz = Tokens.values().map { it -> term.startsWith(it.symbol) }

}




enum class Tokens(val symbol: String) {
    PLUS("+"),
    MINUS("-"),
    DIVISION("/"),
    MULT("*"),
    NEGATIVE("neg"),
    POWER("^"),
    BRACKET_OPEN("("),
    BRACKET_CLOSE(")"),
}