package com.n9mtq4.kotlin.extlib

/**
 * Created by will on 2/2/16 at 9:49 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
private fun stopJavadoc() {}

@Throws(AssertionError::class)
fun assertTrue(condition: Boolean) = assertTrue(condition, "")
@Throws(AssertionError::class)
fun assertTrue(condition: Boolean, msg: String) { if (!condition) throw AssertionError(msg) }
@Throws(AssertionError::class)
fun assertFalse(antiCondition: Boolean, msg: String) = assertTrue(!antiCondition, msg)
