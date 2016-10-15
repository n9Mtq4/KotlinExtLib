package com.n9mtq4.kotlin.extlib.math

import org.junit.Assert.assertEquals
import org.junit.Test
/**
 * Created by will on 10/15/16 at 6:08 PM.
 
 * @author Will "n9Mtq4" Bresnahan
 */
class MathExtKtTest {
	
	@Test
	fun testMaxValueOf() {
		
		assertEquals(4, 5 maxValueOf 4)
		assertEquals(4, 4 maxValueOf 10)
		
	}
	
	@Test
	fun testMinValueOf() {
		
		assertEquals(5, 5 minValueOf 4)
		assertEquals(10, 4 minValueOf 10)
		
	}
	
}
