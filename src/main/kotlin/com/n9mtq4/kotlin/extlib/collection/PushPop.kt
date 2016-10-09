package com.n9mtq4.kotlin.extlib.collection

/**
 * Created by will on 5/19/16 at 7:32 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
fun <T> MutableList<T>.pop(): T {
	return this.removeAt(this.lastIndex)
}

fun <T> MutableList<T>.push(t: T) {
	return this.add(0, t)
}
