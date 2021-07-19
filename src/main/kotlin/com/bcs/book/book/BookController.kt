package com.bcs.book.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book")
class BookController(@Autowired private val bookService: BookService) {

    @GetMapping
    private fun getBooks(): List<Book> {
        return bookService.getBooks()
    }

    @GetMapping("/{bookId}")
    private fun getBook(@PathVariable("bookId") bookID: Int): Book {
        return bookService.getBook(bookID)
    }

    @PostMapping("/add")
    private fun addBooks(@RequestBody bookModel: BookModel) {
        bookService.addBooks(bookModel.books)
    }

    @PutMapping("/{bookId}")
    private fun updateBook(
        @PathVariable("bookId") bookID: Int,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) description: String?
    ) {
        bookService.updateBook(bookID, name, description)
    }

    @DeleteMapping("/{bookId}")
    private fun deleteBook(@PathVariable("bookId") bookID: Int) {
        bookService.deleteBook(bookID)
    }

    @DeleteMapping("/delete")
    private fun deleteBooks(@RequestParam bookIds: List<Int>) {
        bookService.deleteBooks(bookIds)
    }
}