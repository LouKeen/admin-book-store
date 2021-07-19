package com.bcs.book.bookCategory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BookCategoryService(@Autowired private val bookCategoryRepository: BookCategoryRepository) {
    fun getBookCategories(): List<BookCategory> {
        return bookCategoryRepository.findAll()
    }

    fun getBookCategory(bookCategoryId: Int): BookCategory {
        val bookCategoryById = bookCategoryRepository.findById(bookCategoryId)
        if (!bookCategoryById.isPresent) {
            throw IllegalArgumentException("Book Category with id $bookCategoryId does not exist.")
        }
        return bookCategoryById.get()
    }

    fun addBookCategory(bookCategory: BookCategory) {
        val bookCategoryByName = bookCategoryRepository.findBookCategoriesByName(bookCategory.name)
        if (bookCategoryByName.isPresent) {
            throw IllegalArgumentException("Book Category with the name ${bookCategory.name} already exist.")
        }
    }

    fun addBookCategories(bookCategories: List<BookCategory>) {
        bookCategories.forEach { addBookCategory(it) }
    }

    @Transactional
    fun updateBookCategory(bookCategoryId: Int, name: String?, description: String?) {
        val bookCategoryById = bookCategoryRepository.findById(bookCategoryId).orElseThrow { throw IllegalArgumentException("Book Category with id $bookCategoryId does not exist.") }

        if (!name.isNullOrBlank() && !Objects.equals(bookCategoryById.name, name)) {
            if (bookCategoryRepository.findBookCategoriesByName(name).isPresent) {
                throw IllegalArgumentException("Book Category with the name $name already exist.")
            }
            bookCategoryRepository.save(bookCategoryById.copy(name = name))
        }

        if(!description.isNullOrBlank()) {
            bookCategoryRepository.save(bookCategoryById.copy(description = description))
        }
    }

    fun deleteBookCategory(bookCategoryId: Int) {
        val bookCategoryById = bookCategoryRepository.existsById(bookCategoryId)
        if (!bookCategoryById) {
            throw IllegalArgumentException("Book with id $bookCategoryId does not exist.")
        }
    }
}