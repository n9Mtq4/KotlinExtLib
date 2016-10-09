package com.n9mtq4.kotlin.extlib.syntax

import java.util.HashMap
import kotlin.reflect.KProperty

/**
 * Created by will on 4/26/16 at 9:57 PM.
 * 
 * "
 * Note that, since extensions do not actually insert members into classes,
 * there’s no efficient way for an extension property to have a backing field.
 * This is why initializers are not allowed for extension properties.
 * Their behavior can only be defined by explicitly providing getters/setters.
 * "
 * -https://kotlinlang.org/docs/reference/extensions.html#extension-properties
 * 
 * Ha ha ha. This is 2016. Computers are fast enough. Lets forget about
 * efficiency for a minute and give ourselves the ability to make
 * extension variables because their usefulness is extremely useful.
 * 
 * Now please note that the efficiency of this is not at all good.
 * It has to look up the instance of the object in a [HashMap].
 * It also adds an [Int] to the [HashMap]. This int is **never**
 * removed and therefor causes a memory leak. Since ints aren't that
 * large in memory it should not be a huge problem as long as this
 * you are not using extension variables in loop.
 * 
 * Here is some example code:
 * ```kotlin
 * class Person(val name: String)
 * var Person.age: Int by ExtensionVariable()
 * 
 * val personInstance = Person("Name")
 * personInstance.age = 21
 * println(personInstance.age) // prints "21"
 * ```
 * 
 * **INITIALIZED VALUE WARNINGS:**
 * If you are using the default initialized value, give it a non-null
 * value. If a null value is passed in, the result will be the same as
 * if no initialized value is passed at all. For example:
 * ```koltin
 * var Person.mother: Person by ExtensionVariable(null)
 * val personInstance = Person("Name")
 * println(personInstance.mother) // throws UninitializedPropertyAccessException instead of NPE
 * ```
 * 
 * 
 * @param initializedValue the value the variable should be initialized to. **See warnings above.**
 * @param T the return type of the variable
 * 
 * @author Will "n9Mtq4" Bresnahan
 */
class ExtensionVariable<T>(private val initializedValue: T? = null) {
	
	companion object {
		private fun uniqueObjId(o: Any) = System.identityHashCode(o)
	}
	
	private data class Single<T>(var v: T)
	
	private val fields = HashMap<Int, Single<T>>()
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
		val p = fields[uniqueObjId(thisRef!!)] ?:
				if (initializedValue == null) throw UninitializedPropertyAccessException("The variable '${property.name}' in $thisRef hasn't been initialized") 
				else return initializedValue
		return p.v
	}
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, x: T) {
		if (fields[uniqueObjId(thisRef!!)] == null) fields[uniqueObjId(thisRef)] = Single(x)
		else fields[uniqueObjId(thisRef)]?.v = x
	}
	
}

/**
 * Created by will on 4/26/16 at 9:57 PM.
 *
 * "
 * Note that, since extensions do not actually insert members into classes,
 * there’s no efficient way for an extension property to have a backing field.
 * This is why initializers are not allowed for extension properties.
 * Their behavior can only be defined by explicitly providing getters/setters.
 * "
 * -https://kotlinlang.org/docs/reference/extensions.html#extension-properties
 *
 * Ha ha ha. This is 2016. Computers are fast enough. Lets forget about
 * efficiency for a minute and give ourselves the ability to make
 * extension variables because their usefulness is extremely useful.
 *
 * Now please note that the efficiency of this is not at all good.
 * It has to look up the instance of the object in a [HashMap].
 * It also adds an instance of whatever object. This instance
 * is **never** removed and therefor is a big memory leak issue.
 * 
 * **Please use [ExtensionVariable] for a little better performance**
 *
 * Here is some example code:
 * ```kotlin
 * class Person(val name: String)
 * var Person.age: Int by ExtensionVariable&lt;Person, Int&gt;()
 *
 * val personInstance = Person("Name")
 * person.age = 21
 * println(age) // prints "21"
 * ```
 *
 * @param K the class that is being extended
 * @param T the return type of the variable
 *
 * @author Will "n9Mtq4" Bresnahan
 */
@Deprecated("Any object that uses the extension variable is permanently saved in memory", replaceWith = ReplaceWith("ExtensionVariable"))
class InefficientExtensionVariable<K, T>() {
	
	private data class Single<T>(var v: T)
	
	private val fields = HashMap<K, Single<T>>()
	
	operator fun getValue(thisRef: K, property: KProperty<*>): T {
		return (fields[thisRef] ?: throw UninitializedPropertyAccessException("The variable '${property.name}' in $thisRef hasn't been initialized")).v
	}
	
	operator fun setValue(thisRef: K, property: KProperty<*>, x: T) {
		if (fields[thisRef] == null) fields[thisRef] = Single(x)
		else fields[thisRef]?.v = x
	}
	
}
