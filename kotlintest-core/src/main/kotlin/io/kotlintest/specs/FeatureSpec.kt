package io.kotlintest.specs

import io.kotlintest.AbstractSpec
import io.kotlintest.TestCase
import io.kotlintest.TestScope

abstract class FeatureSpec(body: FeatureSpec.() -> Unit = {}) : AbstractSpec() {

  init {
    body()
  }

  final override fun isInstancePerTest(): Boolean = false

  fun feature(name: String, init: FeatureScope.() -> Unit) =
      rootScope.addContainer("Feature: $name", this@FeatureSpec, ::FeatureScope, init)

  inner class FeatureScope : TestScope() {

    fun and(name: String, init: FeatureScope.() -> Unit) =
        addContainer("And: $name", this@FeatureSpec, ::FeatureScope, init)

    fun scenario(name: String, test: () -> Unit): TestCase =
        addTest("Scenario: $name", this@FeatureSpec, test, defaultTestCaseConfig)
  }
}