package com.bcs.book.book

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository: JpaRepository<Book, Int> {

    @Query("SELECT s FROM Book s WHERE s.name = ?1")
    fun findBooksByName(name: String): Optional<Book>
}