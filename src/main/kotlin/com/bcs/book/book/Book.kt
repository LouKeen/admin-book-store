package com.bcs.book.book

import com.bcs.book.bookCategory.BookCategory
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table
data class Book(
    /* Used for sequence generation to auto increment the ID */
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

    /* Code from here until the end are auto generated code from kotlin */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 967762358

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , description = $description )"
    }
}