package com.bcs.book.book

import com.bcs.book.bookCategory.BookCategory
import javax.persistence.*

@Entity
@Table(name = "Book")
data class Book(
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence"
    )
    val id: Int?,
    val name: String,
    val description: String,
) {
    constructor(name: String, description: String) : this(null, name, description)
}