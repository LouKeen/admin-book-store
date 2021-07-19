package com.bcs.book.bookCategory

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table
data class BookCategory(
    /* Used for sequence generation to auto increment the ID */
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
    val description: String
) {
    constructor(name: String, description: String): this(null, name, description)

    /* Code from here until the end are auto generated code from kotlin */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BookCategory

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 17298600

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , description = $description )"
    }
}