package com.n9mtq4.kotlin.extlib.loop

/**
 * Created by will on 5/15/16 at 7:23 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
fun until(condition: Boolean, body: () -> Unit) {
	while (!condition) {
		body()
	}
}
