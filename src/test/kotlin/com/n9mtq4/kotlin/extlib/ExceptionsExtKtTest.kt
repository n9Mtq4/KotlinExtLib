package com.n9mtq4.kotlin.extlib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Created by will on 10/15/16 at 5:48 PM.
 
 * @author Will "n9Mtq4" Bresnahan
 */
class ExceptionsExtKtTest {
	
	private val EXAMPLE_INT: Int = 314
	
	@Test
	fun ignoreAndNull() {
		
		val t: Any? = ignoreAndNull {
			
			throw Exception()
			
		}
		
		assertNull(t)
		
	}
	
	@Test
	fun ignoreAndUnit() {
		
		ignoreAndUnit {
			
			throw Exception()
			
		}
		
	}
	
	@Test
	fun ignoreAndGiven() {
		
		val t: Int = ignoreAndGiven(EXAMPLE_INT) {
			
			throw Exception()
			
		}
		
		assertEquals("exception ignoreAndGiven failed", EXAMPLE_INT, t)
		
		val t1: Int = ignoreAndGiven(EXAMPLE_INT) {
			1
		}
		
		assertEquals("no exception ignoreAndGiven failed", 1, t1)
		
	}
	
}
