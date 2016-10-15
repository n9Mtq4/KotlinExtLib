package com.n9mtq4.kotlin.extlib.syntax

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by will on 10/15/16 at 5:19 PM.
 
 * @author Will "n9Mtq4" Bresnahan
 */
class ExtensionVariableTest {
	
	private val EXAMPLE_INT: Int = 314
	
	val anyVDelegate = ExtensionVariable<Int>()
	var Any.v: Int by anyVDelegate
	
	@Test(expected = UninitializedPropertyAccessException::class)
	fun notInitializedTest() {
		val o = Any()
		val i: Int = o.v
	}
	
	@Test
	fun setAndGet() {
		
		val o = Any()
		o.v = EXAMPLE_INT
		
		assertEquals("o.v does not equal $EXAMPLE_INT (getAndSet)", EXAMPLE_INT, o.v)
		
	}
	
	@Test
	fun free() {
		
		val o = Any()
		o.v = EXAMPLE_INT
		
		assertEquals("o.v does not equal $EXAMPLE_INT (free)", EXAMPLE_INT, o.v)
		
		anyVDelegate.free(o)
		
		val thrown = try {
			
			val i = o.v
			false
			
		}catch (e: UninitializedPropertyAccessException) {
			
			true
			
		}
		
		assertEquals("delegate free failed", true, thrown)
		
	}
	
}
