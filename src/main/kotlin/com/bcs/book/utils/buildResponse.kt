package com.bcs.book.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun buildResponse(status: HttpStatus, action: String, item: String, result: Any) : ResponseEntity<Map<String, Any>> {
    val stat = "status" to status
    val mess = when(status) {
        HttpStatus.CREATED, HttpStatus.OK -> "message" to "Successful in $action $item"
        else -> "message" to "Failed in $action $item"
    }
    val res = "result" to result
    return ResponseEntity.status(status).body(mapOf(stat, mess, res))
}