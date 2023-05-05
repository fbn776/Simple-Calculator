package com.example.simplecalculator


/**
 * A simple queue data structure.
 * @param[T] The data-type queue can hold
 * @property[push] Adds item to the end of the queue
 * @property[pop] Removes the first item of the queue
 * @property[isEmpty] Returns if the queue is empty or not
 */
class Queue<T>(vararg items: T) {
    private val data = mutableListOf<T>(*items)

    fun push(item: T): T {
        data.add(item)
        return item
    }

    fun pushAll(vararg items: T) {
        for (i in items) {
            this.push(i)
        }
    }

    fun pop(): T {
        return data.removeAt(0)
    }

    fun isEmpty(): Boolean {
        return (data.size == 0)
    }

    fun first(): T {
        return data[0]
    }

    override fun toString(): String {
        var ls = "["
        data.forEachIndexed { index, t ->
            ls += t.toString() + (if (index != (data.size - 1)) ", " else "")
        }
        ls += ']'
        return ls
    }
}
