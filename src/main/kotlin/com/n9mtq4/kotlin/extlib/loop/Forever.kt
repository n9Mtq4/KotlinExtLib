package com.n9mtq4.kotlin.extlib.loop

/**
 * Created by will on 3/24/16 at 8:00 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */

inline fun forever(body: () -> Unit) {
	while (true) body.invoke()
}
