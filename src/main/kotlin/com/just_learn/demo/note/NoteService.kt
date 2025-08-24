package com.just_learn.demo.note

import org.springframework.stereotype.Service

class NotFound(id: Long) : RuntimeException("note $id not found")

@Service
class NoteService(private val repo: NoteRepository) {
    fun list(): List<Note> = repo.findAll()
    fun get(id: Long): Note = repo.findById(id) ?: throw NotFound(id)
    fun create(req: CreateNoteReq): Note = repo.save(req.text)
    fun update(id: Long, req: UpdateNoteReq): Note =
        repo.update(id, req.text) ?: throw NotFound(id)
    fun delete(id: Long) { if (!repo.delete(id)) throw NotFound(id) }
}
