package com.bcs.book.bookCategory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bookCategory")
class BookCategoryController(@Autowired private val bookCategoryService: BookCategoryService) {

    @GetMapping
    private fun getBookCategories(): List<BookCategory> {
        return bookCategoryService.getBookCategories()
    }

    @GetMapping("/{bookCategoryId}")
    private fun getBookCategory(@PathVariable("bookCategoryId") bookCategoryId: Int): BookCategory{
        return bookCategoryService.getBookCategory(bookCategoryId)
    }

    @PostMapping("/add")
    private fun addBookCategory(@RequestBody bookCategory: BookCategory) {
        bookCategoryService.addBookCategory(bookCategory)
    }

    @PutMapping("/{bookCategoryId}")
    private fun updateBookCategory(
        @PathVariable("bookCategoryId") bookCategoryId: Int,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) description: String?
    ) {
        bookCategoryService.updateBookCategory(bookCategoryId, name, description)
    }

    @DeleteMapping("/{bookCategoryId}")
    private fun deleteBookCategory(@PathVariable("bookCategoryId") bookCategoryId: Int) {
        bookCategoryService.deleteBookCategory(bookCategoryId)
    }
}