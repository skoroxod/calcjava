package ru.skbkontur.skoroxod.calcjava

import com.github.kittinunf.result.Result

/**
 * Created by skorokhodov on 18.07.2017.
 */
fun Calculate(term: String): Result<String, Exception>{

    var trimmed = term.trim()

    val outputQueue = ArrayList<String>();
    val stack = ArrayList<Token>();

    while (trimmed.length > 0)  {
        var numb = GetNumber(trimmed)
        if(!numb.isNullOrEmpty()) {
            outputQueue.add(numb!!)
            trimmed = trimmed.removePrefix(numb!!).trim();
            continue
        }
        var token = GetToken(trimmed)
        if (token != null) {

        }

        GetNumber(trimmed)?.let {
            outputQueue.add(it)
            trimmed = trimmed.removePrefix(it).trim();
            // тут надо сделать continue но почему-то это сделать нельзя
        }
        GetToken(trimmed)?.let {
            when(it) {
                Token.COMMA -> {
                    // перекладывать токены из стека в очередь до тех пор пока не встретится открывающая скобка
                    // если скобка не встретилась то это значит что ошибка в выражении
                    val res = pекekladyvatIsStackToQueueUntilBracketOpen()
                }
                // оператор оп1 пока пропустим
                Token.BRACKET_OPEN -> { stack.add(it) }

                Token.BRACKET_CLOSE -> {
                    // перекладывать операторы из стека в очередь  пока на вершине стека не появится открываюя скобка
                    stack.dropLast(1)
                }

            }
        }
    }

    return Result.of { term + "100500" }
}


fun GetToken(term: String): Token? =  Token.values().firstOrNull( { term.startsWith(it.symbol) } )

fun GetNumber(term: String): String? {
    if (term.first() == '.') return null
    val number = term.takeWhile { it.isDigit() || it == '.' }
    return if(number.isNullOrEmpty()) null else number
}


enum class Token(val symbol: String) {
    COMMA(","),
    PLUS("+"),
    MINUS("-"),
    DIVISION("/"),
    MULT("*"),
    NEGATIVE("neg"),
    POWER("^"),
    BRACKET_OPEN("("),
    BRACKET_CLOSE(")"),
}