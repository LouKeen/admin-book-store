package com.bcs.book.bookCategory

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookCategoryConfig {

    @Bean
    fun bookCategoryCommandLineRunner(bookCategoryRepository: BookCategoryRepository): CommandLineRunner {
        return CommandLineRunner {
            val bk1 = BookCategory(
                "Category 1",
                "Desc"
            )
            val bk2 = BookCategory(
                "Category 2",
                "Desc"
            )
            bookCategoryRepository.saveAll(listOf(bk1, bk2))
        }
    }
}