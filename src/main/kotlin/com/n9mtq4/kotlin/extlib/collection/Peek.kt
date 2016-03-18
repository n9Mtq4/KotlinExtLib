package com.n9mtq4.kotlin.extlib.collection

/**
 * Created by will on 3/17/16 at 11:46 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
inline fun <T> List<T>.peek(body: (T) -> Unit): List<T> {
	this.forEach(body)
	return this
}

inline fun <T> Iterable<T>.peek(body: (T) -> Unit): Iterable<T> {
	this.forEach(body)
	return this
}

inline fun <T> Sequence<T>.peek(body: (T) -> Unit): Sequence<T> {
	this.forEach(body)
	return this
}

inline fun <T> Collection<T>.peek(body: (T) -> Unit): Collection<T> {
	this.forEach(body)
	return this
}

inline fun <T> List<T>.peekIndexed(body: (Int, T) -> Unit): List<T> {
	this.forEachIndexed(body)
	return this
}

inline fun <T> Iterable<T>.peekIndexed(body: (Int, T) -> Unit): Iterable<T> {
	this.forEachIndexed(body)
	return this
}

inline fun <T> Sequence<T>.peekIndexed(body: (Int, T) -> Unit): Sequence<T> {
	this.forEachIndexed(body)
	return this
}

inline fun <T> Collection<T>.peekIndexed(body: (Int, T) -> Unit): Collection<T> {
	this.forEachIndexed(body)
	return this
}
