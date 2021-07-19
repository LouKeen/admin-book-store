package com.bcs.book.bookCategory

import com.bcs.book.book.Book
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookCategoryTest {

    private val bookCategory = BookCategory("Category Name", "desc")

    @Test
    fun getId() {
        assertEquals(null, bookCategory.id)
    }

    @Test
    fun getName() {
        assertEquals("Category Name", bookCategory.name)
    }

    @Test
    fun getDescription() {
        assertEquals("desc", bookCategory.description)
    }

    @Test
    fun getBooks() {
        assertEquals(emptyList<Book>(), bookCategory.books)
    }
}