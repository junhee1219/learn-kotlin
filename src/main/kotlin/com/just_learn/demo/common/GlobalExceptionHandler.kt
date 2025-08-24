package com.just_learn.demo.common

import com.just_learn.demo.note.NotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ErrorRes(val message: String)

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFound::class)
    fun handleNotFound(e: NotFound): ResponseEntity<ErrorRes> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorRes(e.message ?: "not found"))
}
