package com.n9mtq4.kotlin.extlib.syntax

/**
 * Created by will on 3/21/16 at 9:11 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
inline fun <T> def(body: () -> T) = body()
