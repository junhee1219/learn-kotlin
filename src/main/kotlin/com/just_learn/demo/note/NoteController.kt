package com.just_learn.demo.note

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notes")
class NoteController(private val svc: NoteService) { // 오.. 생성자주입을 신박하게하네

    @GetMapping
    fun list(): List<Note> = svc.list()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Note = svc.get(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody req: CreateNoteReq): Note = svc.create(req)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: UpdateNoteReq): Note =
        svc.update(id, req)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = svc.delete(id)
}
