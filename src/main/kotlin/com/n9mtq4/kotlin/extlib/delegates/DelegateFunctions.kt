package com.n9mtq4.kotlin.extlib.delegates

import kotlin.reflect.KProperty

/**
 * Created by will on 10/15/16 at 8:36 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */

// https://gist.github.com/pardom/b4157e6e4c512546087d#file-properties-kt-L50
/**
 * Gets the delegate for the kotlin property.
 * 
 * @param prop The kotlin property
 * @param T The return type of the kotlin property
 * @return The delegate that managers that property
 * */
fun <T> Any.getDelegate(prop: KProperty<T>): Any {
	return javaClass.getDeclaredField("${prop.name}\$delegate").let {
		it.isAccessible = true
		it.get(this)
	}
}
