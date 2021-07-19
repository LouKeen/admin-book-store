package com.bcs.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class BookApplication

fun main(args: Array<String>) {
	runApplication<BookApplication>(*args)
}