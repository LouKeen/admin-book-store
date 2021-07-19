package com.bcs.book.bookCategory

import com.fasterxml.jackson.annotation.JsonCreator

data class BookCategoryModel @JsonCreator constructor(
    val bookCategories: List<BookCategory>
)