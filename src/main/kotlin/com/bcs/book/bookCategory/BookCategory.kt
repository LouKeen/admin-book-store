package com.bcs.book.bookCategory

import com.bcs.book.book.Book
import javax.persistence.*

@Entity
@Table
data class BookCategory(
    @Id
    @SequenceGenerator(
        name = "book_category_sequence",
        sequenceName = "book_category_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_category_sequence"
    )
    val id: Int?,
    val name: String,
    val description: String,

    @ManyToMany(targetEntity = Book::class)
    val books: List<Book>?
) {
    constructor(name: String, description: String): this(null, name, description, emptyList())
    constructor(name: String, description: String, books: List<Book>): this(null, name, description, books)
}