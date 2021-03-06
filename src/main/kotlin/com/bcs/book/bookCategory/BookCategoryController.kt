package com.bcs.book.bookCategory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bookCategory")
class BookCategoryController(@Autowired private val bookCategoryService: BookCategoryService) {
    /* API for getting all the list of book categories stored in the database. */
    @GetMapping
    private fun getBookCategories(): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.getBookCategories()
    }

    /* API for getting a specific book category stored in the database. */
    @GetMapping("/{bookCategoryId}")
    private fun getBookCategory(@PathVariable("bookCategoryId") bookCategoryId: Int): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.getBookCategory(bookCategoryId)
    }

    /* API for adding a list of book categories in the database. */
    @PostMapping("/add")
    private fun addBookCategories(@RequestBody bookCategoryModel: BookCategoryModel): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.addBookCategories(bookCategoryModel.bookCategories)
    }

    /* API for updating a book category in the database. */
    @PutMapping("/{bookCategoryId}")
    private fun updateBookCategory(
        @PathVariable("bookCategoryId") bookCategoryId: Int,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) description: String?
    ): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.updateBookCategory(bookCategoryId, name, description)
    }

    /* API for deleting a specific book category in the database. */
    @DeleteMapping("/{bookCategoryId}")
    private fun deleteBookCategory(@PathVariable("bookCategoryId") bookCategoryId: Int): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.deleteBookCategory(bookCategoryId)
    }

    /* API for deleting a set of book categories in the database */
    @DeleteMapping("/delete")
    private fun deleteBookCategories(@RequestParam bookCategoryIds: List<Int>): ResponseEntity<Map<String, Any>> {
        return bookCategoryService.deleteBookCategories(bookCategoryIds)
    }
}