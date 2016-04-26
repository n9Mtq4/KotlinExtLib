package com.n9mtq4.kotlin.extlib

/**
 * Created by will on 3/17/16 at 10:10 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
inline fun <T> ignoreAndNull(body: () -> T): T? {
	try {
		return body.invoke()
	}catch (ignored: Exception) {}
	return null
}

inline fun <T> pstAndNull(body: () -> T): T? {
	try {
		return body.invoke()
	}catch (e: Exception) {
		e.printStackTrace()
	}
	return null
}

inline fun ignoreAndUnit(body: () -> Unit) {
	try {
		body.invoke()
	}catch (ignored: Exception) {}
}

inline fun pstAndUnit(body: () -> Unit) {
	try {
		body.invoke()
	}catch (e: Exception) {
		e.printStackTrace()
	}
}

inline fun <T> ignoreAndGiven(returnValue: T, body: () -> T): T {
	try {
		return body.invoke()
	}catch (ignored: Exception) {}
	return returnValue
}

inline fun <T> pstAndGiven(returnValue: T, body: () -> T): T {
	try {
		return body.invoke()
	}catch (e: Exception) {
		e.printStackTrace()
	}
	return returnValue
}

inline fun ignore(body: () -> Unit) = ignoreAndUnit(body)
inline fun pst(body: () -> Unit) = pstAndUnit(body)
