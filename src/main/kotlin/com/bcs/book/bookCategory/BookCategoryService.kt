package com.bcs.book.bookCategory

import com.bcs.book.utils.buildResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BookCategoryService(@Autowired private val bookCategoryRepository: BookCategoryRepository) {
    fun getBookCategories(): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            HttpStatus.OK to bookCategoryRepository.findAll()
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "getting", "book category", result)
    }

    fun getBookCategory(bookCategoryId: Int): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            val bookCategoryById = bookCategoryRepository.findById(bookCategoryId)
            if (!bookCategoryById.isPresent) {
                throw IllegalArgumentException("Book Category with id $bookCategoryId does not exist.")
            }
            HttpStatus.OK to bookCategoryById.get()
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "getting", "book category", result)
    }

    fun addBookCategory(bookCategory: BookCategory) {
        val bookCategoryByName = bookCategoryRepository.findBookCategoriesByName(bookCategory.name)
        if (bookCategoryByName.isPresent) {
            throw IllegalArgumentException("Book Category with the name ${bookCategory.name} already exist.")
        }
        bookCategoryRepository.save(bookCategory)
    }

    fun addBookCategories(bookCategories: List<BookCategory>): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            bookCategories.forEach { addBookCategory(it) }
            HttpStatus.CREATED to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "creating", "book categories", result)
    }

    @Transactional
    fun updateBookCategory(bookCategoryId: Int, name: String?, description: String?): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
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
            HttpStatus.OK to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "updating", "book category", result)
    }

    fun deleteBookCategory(bookCategoryId: Int): ResponseEntity<Map<String, Any>> {
        val(status, result) = try {
            val bookCategoryById = bookCategoryRepository.existsById(bookCategoryId)
            if (!bookCategoryById) {
                throw IllegalArgumentException("Book with id $bookCategoryId does not exist.")
            }
            HttpStatus.OK to bookCategoryRepository.deleteById(bookCategoryId)
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "deleting", "book category", result)
    }

    fun deleteBookCategories(bookCategoryIds: List<Int>): ResponseEntity<Map< String, Any>> {
        val(status, result) = try {
            bookCategoryIds.forEach { deleteBookCategory(it) }
            HttpStatus.OK to ""
        } catch (e: Exception) {
            HttpStatus.BAD_REQUEST to e
        }
        return buildResponse(status, "deleting", "book categories", result)
    }
}