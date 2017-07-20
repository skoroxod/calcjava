package ru.skbkontur.skoroxod.calcjava

import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by skorokhodov on 20.07.2017.
 */
class CalculateTest {

    @Test
    fun testSpaces() {
        val res = ParseTerms("  5 + 6+8.090 - 5 -3030 -0989 -        098 ")
        res.success { assertEquals(it.size,13 ) }
        res.failure { println(it);fail("такого не может быть") }
    }

    @Test
    fun testFuncArguments2NotWorking() {
        var res = ParseTerms("max(45,,,43)")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок или пропущена запятая в аргументах функции") }
        res.success { println(it);fail("такого не может быть") }
    }

    @Test
    fun testFuncArguments() {
        var res = ParseTerms("(5+6) + max45,43)")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок или пропущена запятая в аргументах функции") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("max(45,43 + 89")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("max(45,43 + 89)")
        res.failure { println(it);fail("такого не может быть") }
        res.success {  assertEquals(it.size, 5)}

        res = ParseTerms("max(45 + 43 + 89)")
        res.failure { println(it);fail("такого не может быть") }
        res.success {  assertEquals(it.size, 6)}

        res = ParseTerms("max(45 + 43, 89)")
        res.failure { println(it);fail("такого не может быть") }
        res.success {  assertEquals(it.size, 5)}

        res = ParseTerms("max(45,43 + 89, 23)")
        res.failure { println(it);fail("такого не может быть") }
        res.success {  assertEquals(it.size, 6)}

    }

    @Test
    fun testUnknownSymbol() {
        var res = ParseTerms("(5+6) + cos(45)")
        res.failure { assertEquals(it.message,"Выражение содержит непонятные символы") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("(xz+6) + 45")
        res.failure { assertEquals(it.message,"Выражение содержит непонятные символы") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("(6 x 6) + 45")
        res.failure { assertEquals(it.message,"Выражение содержит непонятные символы") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("(6 + 6) = 45")
        res.failure { assertEquals(it.message,"Выражение содержит непонятные символы") }
        res.success { println(it);fail("такого не может быть") }
    }

    @Test
    fun testBracketsTerm() {
        var res = ParseTerms("(5+6)")
        res.success {assertEquals(it.size,3 )}

        res = ParseTerms("(5+6")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("(5+6))")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок") }
        res.success { println(it);fail("такого не может быть") }

        res = ParseTerms("( ( (6+7)-(5+6) )+8)")
        res.success {assertEquals(it.size,9 )}
        res.failure { println(it);fail("такого не может быть") }

        res = ParseTerms("((((((((5+6))+ 98  +234 +234 ) + 234 + 234)")
        res.failure { assertEquals(it.message,"В выражении нарушен баланс скобок") }
        res.success { println(it);fail("такого не может быть") }

    }
}