package com.just_learn.demo.note

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

interface NoteRepository {
    fun findAll(): List<Note>
    fun findById(id: Long): Note?
    fun save(text: String): Note
    fun update(id: Long, text: String): Note?
    fun delete(id: Long): Boolean
}

@Repository
class InMemoryNoteRepository : NoteRepository {
    private val seq = AtomicLong(0)
    private val store = ConcurrentHashMap<Long, Note>()

    override fun findAll(): List<Note> =
        store.values.sortedByDescending { it.createdAt }

    override fun findById(id: Long): Note? = store[id]

    override fun save(text: String): Note =
        Note(id = seq.incrementAndGet(), text = text).also { store[it.id] = it }

    override fun update(id: Long, text: String): Note? =
        store[id]?.also { it.text = text }

    override fun delete(id: Long): Boolean =
        store.remove(id) != null
}
