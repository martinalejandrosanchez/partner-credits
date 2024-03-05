package com.pedidosya.partner_credits.domain

import org.mockito.Mockito

object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}
