package com.example.simplecalculator

fun <T>MutableList<T>.lastElm(): T {
    return this[this.size-1]
}