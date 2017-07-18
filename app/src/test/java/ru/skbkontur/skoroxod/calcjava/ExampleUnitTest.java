package ru.skbkontur.skoroxod.calcjava;

import org.junit.Test;

import kotlin.Pair;

import static org.junit.Assert.*;
import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.Calculate;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Pair<Boolean, String> res = Calculate("2 + 2");
        assertEquals(true, res.getFirst());
        assertEquals("4", res.getSecond());
    }
}