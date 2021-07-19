package com.bcs.book.book

import com.fasterxml.jackson.annotation.JsonCreator

data class BookModel @JsonCreator constructor(
    val books: List<Book>
)