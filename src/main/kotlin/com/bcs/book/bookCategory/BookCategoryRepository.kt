package com.bcs.book.bookCategory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookCategoryRepository: JpaRepository<BookCategory, Int> {

    @Query("SELECT s FROM BookCategory s WHERE s.name = ?1")
    fun findBookCategoriesByName(name: String): Optional<BookCategory>
}