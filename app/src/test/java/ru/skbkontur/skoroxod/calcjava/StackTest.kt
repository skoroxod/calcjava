package ru.skbkontur.skoroxod.calcjava

import com.github.kittinunf.result.Result
import com.github.kittinunf.result.Result.Failure
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.map
import com.github.kittinunf.result.success
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by skorokhodov on 20.07.2017.
 */
class StackTest {

    @Test
    fun testPopAll() {
        val v = ArrayList<Int>()
        v.push(100401)
        v.push(100402)
        var head = v.popAll()
        assertEquals(head.size, 2)
        assertEquals(head[0], 100402)
        assertEquals(head[1], 100401)
    }

    @Test
    fun testPopAllEmpty() {
        val v = ArrayList<Int>()
        var head = v.popAll()
        assertTrue(head.isEmpty())
    }

    @Test
    fun testPop2() {
        val v = ArrayList<Int>()
        v.push(100401)
        v.push(100402)
        var head = v.pop2()
    }

    @Test
    fun testPopUntil() {
        val v = ArrayList<Int>()
        v.push(1)
        v.push(200)
        v.push(3)
        v.push(4)

        val res = v.popUntilElement(200).get()
        assertEquals(res.size, 2)
        assertEquals(v.size, 2)
        assertEquals(v.peek(), 200)
        assertEquals(res[0], 4)
        assertEquals(res[1], 3)
    }

    @Test
    fun testPopUntilEmpty() {
        val v = ArrayList<Int>()
        v.push(1)
        v.push(200)
        v.push(3)
        v.push(400)

        val res = v.popUntilElement(400).get()
        assertEquals(res.size, 0)
        assertEquals(v.size, 4)
        assertEquals(v.peek(), 400)
    }

    @Test
    fun testPopUntilError() {
        val v = ArrayList<Int>()
        v.push(1)
        v.push(200)
        v.push(3)
        v.push(400)

        val res = v.popUntilElement(500)
        res.success { fail("must be error") }
        res.failure { assertEquals(it.message,"item not found") }
//        assertEquals(v.size, 4)
    }


    @Test
    fun testElementOrder() {
        val v = ArrayList<Int>()
        v.push(100401)
        v.push(100402)
        var head = v.pop()
        assertEquals(head, 100402)

        head = v.pop()
        assertEquals(head, 100401)
    }

    @Test
    fun testEmptyness() {
        val v = ArrayList<Int>()

        var head = v.peek()
        assertNull(head)

        head = v.pop()
        assertNull(head)

        v.push(100401)
        head = v.pop()
        assertEquals(head, 100401)
        head = v.pop()
        assertNull(head)
    }
    @Test
        fun testSimpleOperation() {
            val v = ArrayList<Int>()
            v.add(1)
            v.add(2)
            v.add(3)
            assertEquals(v.size, 3)

            var head = v.peek()
            assertEquals(head, 3)
            assertEquals(v.size, 3)

            head = v.pop()
            assertEquals(head, 3)
            assertEquals(v.size, 2)

            v.push(100500)
            assertEquals(v.size, 3)
            head = v.peek()
            assertEquals(head, 100500)
        }


}