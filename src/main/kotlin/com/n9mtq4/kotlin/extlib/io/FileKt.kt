@file:JvmName("KotlinFile")
@file:Suppress("unused")

package com.n9mtq4.kotlin.extlib.io;

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.net.URI

/**
 * Created by will on 12/30/15 at 9:53 PM.
 *
 * A python like implementation of files
 * this code is dense, ugly, and undocumented, but it works
 *
 * @author Will "n9Mtq4" Bresnahan
 */
private fun stopTheJavadoc() {}

fun open(f: File, t: String) = open(f, t.contains('r'), t.contains('w'), t.contains('+'))
fun open(fPath: String, t: String) = open(File(fPath), t)
fun open(f: File, canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false) = FileKt(f.absolutePath, canRead, canWrite, append)
fun open(fPath: String , canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false) = FileKt(fPath, canRead, canWrite, append)

open class FileKt(file: File, val canRead: Boolean = false, val canWrite: Boolean = false, val append: Boolean = false, var autoFlush: Boolean = true) : File(file.absolutePath), Closeable {
	
	private val bufferedReader: BufferedReader?
	private val bufferedWriter: BufferedWriter?
	
	constructor(pathname: String, canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false): this(File(pathname), canRead, canWrite, append)
	constructor(parent: String, child: String, canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false): this(File(parent, child), canRead, canWrite, append)
	constructor(parent: File, child: String, canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false): this(File(parent, child), canRead, canWrite, append)
	constructor(uri: URI, canRead: Boolean = false, canWrite: Boolean = false, append: Boolean = false): this(File(uri), canRead, canWrite, append)
	
	init {
		if (canWrite) {
			this.bufferedWriter = BufferedWriter(FileWriter(this, append))
		}else this.bufferedWriter = null
		if (canRead) {
			this.bufferedReader = BufferedReader(FileReader(this))
		}else this.bufferedReader = null
	}
	
	fun read(): String? {
		assertCanRead()
		return bufferedReader?.readLine()
	}
	
	fun readText(): String? {
		assertCanRead()
		return bufferedReader?.readText()
	}
	
	fun readLines(): List<String>? {
		assertCanRead()
		return bufferedReader?.readLines()
	}
	
	fun write(msg: String): FileKt {
		assertCanWrite()
		bufferedWriter!!.append(msg)
		if (autoFlush) flushWriter()
		return this
	}
	
	fun writeln(msg: String): FileKt {
		assertCanWrite()
		write(msg + "\n")
		return this
	}
	
	fun flushWriter() {
		assertCanWrite() // TODO: should I throw exception or silently fail?
		bufferedWriter?.flush()
	}
	
	override fun close() {
		bufferedReader?.close()
		bufferedWriter?.close()
	}
	
	private fun assertCanWrite() {
		if (!canWrite) throw IllegalStateException("This can not write to a file!")
	}
	
	private fun assertCanRead() {
		if (!canRead) throw IllegalStateException("This can not read a file")
	}
	
	override fun delete(): Boolean {
		close()
		return super.delete()
	}
	
}
