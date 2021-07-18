package com.bcs.book.book

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookConfig {

    @Bean
    fun bookCommandLineRunner(bookRepository: BookRepository): CommandLineRunner {
        return CommandLineRunner {
            val bk1 = Book(
                "Book 1",
                "Desc"
            )
            val bk2 = Book(
                "Book 2",
                "Desc"
            )
            bookRepository.saveAll(listOf(bk1, bk2))
        }
    }
}