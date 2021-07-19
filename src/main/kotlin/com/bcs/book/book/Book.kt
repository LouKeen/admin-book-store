package com.bcs.book.book

import com.bcs.book.bookCategory.BookCategory
import com.fasterxml.jackson.annotation.JsonCreator
import javax.persistence.*

@Entity
@Table(name = "book")
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

    @ManyToMany(targetEntity = BookCategory::class)
    val categories: List<BookCategory>?
){
    constructor(name: String, description: String) : this(null, name, description, emptyList())
    constructor(name: String, description: String,categories: List<BookCategory>) : this(null, name, description, categories)
}