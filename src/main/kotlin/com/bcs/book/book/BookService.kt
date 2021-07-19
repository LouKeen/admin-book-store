package com.bcs.book.book

import com.bcs.book.bookCategory.BookCategory
import com.bcs.book.bookCategory.BookCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BookService(@Autowired private val bookRepository: BookRepository, @Autowired private val bookCategoryRepository: BookCategoryRepository) {
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun getBook(bookId: Int): Book {
        val bookById = bookRepository.findById(bookId)
        if (!bookById.isPresent) {
            throw IllegalArgumentException("Book with id $bookId does not exist.")
        }
        return bookById.get()
    }

    fun addBook(book: Book) {
        val bookByName = bookRepository.findBooksByName(book.name)
        if (bookByName.isPresent) {
            throw IllegalArgumentException("Book with the name ${book.name} already exist.")
        }
        bookRepository.save(book)
    }

    fun addBooks(books: List<Book>) {
        books.forEach {
            addBook(it)
        }
    }

    @Transactional
    fun updateBook(bookId: Int, name: String?, description: String?, categoryIds: List<Int>?) {
        val bookById = bookRepository.findById(bookId).orElseThrow { throw IllegalArgumentException("Book with id $bookId does not exist.") }

        if (!name.isNullOrBlank() && !Objects.equals(bookById.name, name)) {
            if (bookRepository.findBooksByName(name).isPresent) {
                throw IllegalArgumentException("Book with the name $name already exist.")
            }
            bookRepository.save(bookById.copy(name = name))
        }

        if(!description.isNullOrBlank()) {
            bookRepository.save(bookById.copy(description = description))
        }

        if (!categoryIds.isNullOrEmpty()) {
            val categoryList = mutableListOf<BookCategory>()

            categoryIds.forEach {
                val bookCategory = bookCategoryRepository.findById(it).orElseThrow { throw IllegalArgumentException("Book Category with the id $it was not found.") }
                categoryList.add(bookCategory)
            }

            if (categoryList.isNotEmpty()) {
                bookRepository.save(bookById.copy(categories = categoryList))
            }
        }
    }

    fun deleteBook(bookId: Int) {
        val bookById = bookRepository.existsById(bookId)
        if (!bookById) {
            throw IllegalArgumentException("Book with id $bookId does not exist.")
        }
        bookRepository.deleteById(bookId)
    }

    fun deleteBooks(bookIds: List<Int>) {
        bookIds.forEach { deleteBook(it) }
    }
}