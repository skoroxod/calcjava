package ru.skbkontur.skoroxod.calcjava;

import com.github.kittinunf.result.Result;

import org.junit.Test;


import static org.junit.Assert.*;
import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.Calculate;
import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.GetNumber;
import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.GetToken;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Result<String, Exception> res = Calculate("2 + 2");
//        assertTrue(res == Result.Success);
//        assertEquals("4", res.get());
    }

    @Test
    public void GetNumberIsCorrect() throws Exception {

        String xz = GetNumber("23424");
        assertEquals(xz, "23424");

        xz = GetNumber("4+(5-6)");
        assertEquals(xz, "4");

        xz = GetNumber("-4");
        assertNull(xz);

        xz = GetNumber("(4-5)");
        assertNull(xz);

        xz = GetNumber("-(4-5)");
        assertNull(xz);

        xz = GetNumber(" 4");
        assertNull(xz);

        xz = GetNumber("3.4");
        assertEquals(xz, "3.4");

        xz = GetNumber("23.4");
        assertEquals(xz, "23.4");

        xz = GetNumber("23.424");
        assertEquals(xz, "23.424");

        xz = GetNumber(".424");
        assertNull(xz);

        xz = GetNumber(".424");
        assertNull(xz);

    }

    @Test
    public void GetTokenIsCorrect() throws Exception {
        Token xz = GetToken("23424");
        assertNull(xz);

        xz = GetToken("23424");
        assertNull(xz);

        xz = GetToken("+ 23424");
        assertEquals(xz, Token.PLUS);
        xz = GetToken("( 23424)");
        assertEquals(xz, Token.BRACKET_OPEN);
        xz = GetToken(")23424)");
        assertEquals(xz, Token.BRACKET_CLOSE);
        xz = GetToken("/ 23424)");
        assertEquals(xz, Token.DIVISION);
        xz = GetToken("-    23424)");
        assertEquals(xz, Token.MINUS);
        xz = GetToken("*23424)");
        assertEquals(xz, Token.MULT);
        xz = GetToken("neg23424)");
        assertEquals(xz, Token.NEGATIVE);
        xz = GetToken("^    23424)");
        assertEquals(xz, Token.POWER);
        xz = GetToken(",    23424)");
        assertEquals(xz, Token.COMMA);
        xz = GetToken(",2323,23234,234)");
        assertEquals(xz, Token.COMMA);
    }
}

