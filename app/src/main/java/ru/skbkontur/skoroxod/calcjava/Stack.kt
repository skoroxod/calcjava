package ru.skbkontur.skoroxod.calcjava

import com.github.kittinunf.result.Result

/**
 * Created by skorokhodov on 20.07.2017.
 */

fun <T> ArrayList<T>.push(item: T) {
    this.add(item)
}
fun <T> ArrayList<T>.peek(): T?{
    if(this.isEmpty()) return null
    return this.last()
}

fun <T : Any> ArrayList<T>.pop2(): Result<T, Exception> {
    return Result.of( { this.removeAt(this.size - 1) })
}


fun <T> ArrayList<T>.pop(): T? {
    if(this.isEmpty()) return null
    return this.removeAt(this.size - 1)
}

fun <T> ArrayList<T>.popAll(): List<T> = this.reversed()

fun <T> ArrayList<T>.popUntilElement(item: T): Result<List<T>, Exception> {
//    TODO("вообще-то это надо переписать")
//    TODO("не надо по одному элементу делать pop, а  надо найти в массиве нужный элемент и вернуть хвост массива так получается и долго и если элемент не найдет то весь стек очищается")

    val list =  mutableListOf<T>()
    while(this.peek() != null && this.peek() != item) {
        val xz= this.pop()!!
        list.add(xz)
    }
    if (this.isEmpty()) return Result.error(Exception("item not found"))
    return Result.of { list }
}
