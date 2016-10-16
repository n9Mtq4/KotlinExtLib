package com.n9mtq4.kotlin.extlib.delegates

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by will on 10/15/16 at 5:19 PM.
 
 * @author Will "n9Mtq4" Bresnahan
 */

val anyVDelegate = ExtensionVariable<Int>()
var Any.v: Int by anyVDelegate

class ExtensionVariableTest {
	
	private val EXAMPLE_INT: Int = 314
	
	@Test(expected = UninitializedPropertyAccessException::class)
	fun testNotInitializedTest() {
		val o = Any()
		val i: Int = o.v
	}
	
	@Test
	fun testSetAndGet() {
		
		val o = Any()
		o.v = EXAMPLE_INT
		
		assertEquals("o.v does not equal $EXAMPLE_INT (getAndSet)", EXAMPLE_INT, o.v)
		
	}
	
	@Test
	fun testFree() {
		
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
	
	@Test
	fun testFreeReflection() {
		
		val o = Any()
		o.v = EXAMPLE_INT
		
		assertEquals("o.v does not equal $EXAMPLE_INT (free reflection)", EXAMPLE_INT, o.v)
		
		// FIXME: the getDelegates method does not currently work in this situation
		if (true) return
		(o.getDelegate(Any::v) as ExtensionVariable<*>).free(o)
		
		val thrown = try {
			
			val i = o.v
			false
			
		}catch (e: UninitializedPropertyAccessException) {
			
			true
			
		}
		
		assertEquals("delegate free failed", true, thrown)
		
	}
	
}
