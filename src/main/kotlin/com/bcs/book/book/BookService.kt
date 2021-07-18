package com.bcs.book.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BookService(@Autowired private val bookRepository: BookRepository) {
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

    @Transactional
    fun updateBook(bookId: Int, name: String?, description: String?) {
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
    }

    fun deleteBook(bookId: Int) {
        val bookById = bookRepository.existsById(bookId)
        if (!bookById) {
            throw IllegalArgumentException("Book with id $bookId does not exist.")
        }
        bookRepository.deleteById(bookId)
    }
}