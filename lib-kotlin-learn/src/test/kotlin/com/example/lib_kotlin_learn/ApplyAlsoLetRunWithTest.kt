package com.example.lib_kotlin_learn

import org.junit.Test

import org.junit.Before

class ApplyAlsoLetRunWithTest {

    private lateinit var a:ApplyAlsoLetRunWith ;

    @Before
    fun setUp() {
        a = ApplyAlsoLetRunWith()
    }

    @Test
    fun testApply() {
        a.testApply()
    }
    @Test
    fun testUpperCase() {
        a.upperCase()
    }
    @Test
    fun testAlso() {
        a.testAlso()
    }

    @Test
    fun testLet() {
        a.testLet()
    }

    @Test
    fun testRun() {
        a.testRun()
    }

    @Test
    fun testWith() {
        a.testWith()
    }
}