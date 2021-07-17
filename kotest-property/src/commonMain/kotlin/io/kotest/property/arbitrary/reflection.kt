@file:Suppress("UNCHECKED_CAST")

package io.kotest.property.arbitrary

import io.kotest.mpp.bestName
import io.kotest.property.Arb
import kotlin.reflect.KClass

/**
 * Returns an [Arb] for the given kclass by first checking for a multiplatform
 * arb, and then falling back to platform specific arbs, before throwing if no
 * suitable arb can be found.
 */
@Suppress("UNCHECKED_CAST")
expect inline fun <reified A : Any> Arb.Companion.default(): Arb<A>

/**
 * Returns an [Arb] for the given kclass if one is available on all platforms,
 * otherwise returns null.
 */
fun <A : Any> defaultForClass(kClass: KClass<*>): Arb<A>? {
   return when (kClass.bestName()) {
      "java.lang.String", "kotlin.String", "String" -> Arb.string() as Arb<A>
      "java.lang.Character", "kotlin.Char", "Char" -> Arb.char() as Arb<A>
      "java.lang.Long", "kotlin.Long", "Long" -> Arb.long() as Arb<A>
      "kotlin.ULong", "ULong" -> Arb.uLong() as Arb<A>
      "java.lang.Integer", "kotlin.Int", "Int" -> Arb.int() as Arb<A>
      "kotlin.UInt", "UInt" -> Arb.uInt() as Arb<A>
      "java.lang.Short", "kotlin.Short", "Short" -> Arb.short() as Arb<A>
      "kotlin.UShort", "UShort" -> Arb.uShort() as Arb<A>
      "java.lang.Byte", "kotlin.Byte", "Byte" -> Arb.byte() as Arb<A>
      "kotlin.UByte", "UByte" -> Arb.uByte() as Arb<A>
      "java.lang.Double", "kotlin.Double", "Double" -> Arb.double() as Arb<A>
      "java.lang.Float", "kotlin.Float", "Float" -> Arb.float() as Arb<A>
      "java.lang.Boolean", "kotlin.Boolean", "Boolean" -> Arb.boolean() as Arb<A>
      else -> null
   }
}

class NoGeneratorFoundException(msg: String) : RuntimeException(msg)
