package com.bcs.book.book

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BookTest {

    private val book = Book("Book Name", "desc")

    @Test
    fun getId() {
        assertEquals(null, book.id)
    }

    @Test
    fun getName() {
        assertEquals("Book Name", book.name)
    }

    @Test
    fun getDescription() {
        assertEquals("desc", book.description)
    }

    @Test
    fun getCategories() {
        assertEquals(emptyList(), book.categories)
    }
}