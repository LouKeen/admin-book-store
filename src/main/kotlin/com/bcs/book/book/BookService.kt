package com.bcs.book.book

import com.bcs.book.bookCategory.BookCategory
import com.bcs.book.bookCategory.BookCategoryRepository
import com.bcs.book.utils.buildResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BookService(@Autowired private val bookRepository: BookRepository, @Autowired private val bookCategoryRepository: BookCategoryRepository) {
    fun getBooks(): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            HttpStatus.OK to bookRepository.findAll()
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "getting", "books", result)
    }

    fun getBook(bookId: Int): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            val bookById = bookRepository.findById(bookId)
            if (!bookById.isPresent) {
                throw IllegalArgumentException("Book with id $bookId does not exist.")
            }
            HttpStatus.OK to bookById.get()
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "getting", "book", result)
    }

    fun addBook(book: Book) {
        val bookByName = bookRepository.findBooksByName(book.name)
        if (bookByName.isPresent) {
            throw IllegalArgumentException("Book with the name ${book.name} already exist.")
        }
        bookRepository.save(book)
    }

    fun addBooks(books: List<Book>): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            books.forEach {
                addBook(it)
            }
            HttpStatus.CREATED to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "creating", "book/s", result)
    }

    @Transactional
    fun updateBook(bookId: Int, name: String?, description: String?, categoryIds: List<Int>?): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
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
            HttpStatus.OK to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "updating", "book", result)
    }

    fun deleteBook(bookId: Int): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            val bookById = bookRepository.existsById(bookId)

            if (!bookById) {
                throw IllegalArgumentException("Book with id $bookId does not exist.")
            }
            HttpStatus.OK to bookRepository.deleteById(bookId)
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "deleting", "book", result)
    }

    fun deleteBooks(bookIds: List<Int>): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            bookIds.forEach { deleteBook(it) }
            HttpStatus.OK to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "deleting", "books", result)
    }
}