package com.bcs.book.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book")
class BookController(@Autowired private val bookService: BookService) {
    /* API for getting all the list of books stored in the database. */
    @GetMapping
    private fun getBooks(): ResponseEntity<Map<String, Any>> {
        return bookService.getBooks()
    }

    /* API for getting a specific book stored in the database. */
    @GetMapping("/{bookId}")
    private fun getBook(@PathVariable("bookId") bookID: Int): ResponseEntity<Map<String, Any>> {
        return bookService.getBook(bookID)
    }

    /* API for adding a list of books in the database. */
    @PostMapping("/add")
    private fun addBooks(@RequestBody bookModel: BookModel): ResponseEntity<Map<String, Any>> {
        return bookService.addBooks(bookModel.books)
    }

    /* API for updating a book in the database. */
    @PutMapping("/{bookId}")
    private fun updateBook(
        @PathVariable("bookId") bookID: Int,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) description: String?,
        @RequestParam(required = false) categoryIds: List<Int>?
    ): ResponseEntity<Map<String, Any>> {
        return  bookService.updateBook(bookID, name, description, categoryIds)
    }

    /* API for deleting a specific book in the database. */
    @DeleteMapping("/{bookId}")
    private fun deleteBook(@PathVariable("bookId") bookID: Int): ResponseEntity<Map<String, Any>> {
        return bookService.deleteBook(bookID)
    }

    /* API for deleting a set of books in the database */
    @DeleteMapping("/delete")
    private fun deleteBooks(@RequestParam bookIds: List<Int>): ResponseEntity<Map<String, Any>> {
        return bookService.deleteBooks(bookIds)
    }
}