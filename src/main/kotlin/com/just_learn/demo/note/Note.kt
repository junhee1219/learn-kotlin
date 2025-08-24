package com.just_learn.demo.note

import java.time.Instant

data class Note(
    val id: Long,
    var text: String,
    val createdAt: Instant = Instant.now()
)

data class CreateNoteReq(val text: String)
data class UpdateNoteReq(val text: String)
