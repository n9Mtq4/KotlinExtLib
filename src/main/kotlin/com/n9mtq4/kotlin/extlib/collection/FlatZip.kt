package com.n9mtq4.kotlin.extlib.collection

/**
 * Created by will on 6/22/17 at 9:13 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
fun <R> List<R>.flatZip(other: List<R>): List<R> = this.zip(other).flatMap { listOf(it.first, it.second) }
