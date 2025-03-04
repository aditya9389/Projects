package com.crud.fnpblog.controller;

import com.crud.fnpblog.model.Note;
import com.crud.fnpblog.services.NoteService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000") // Adjust frontend URL if needed
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/getNotes")
    public ResponseEntity<List<Note>> getUserNotesByToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(noteService.getNotesByToken(token));
    }

    @PostMapping("/createNote/{username}")
    public ResponseEntity<Note> createNote(@PathVariable String username, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(username, note));
    }

    @PutMapping("/updateNote/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId, String username,@RequestBody Note updatedNote) {
        return ResponseEntity.ok(noteService.updateNote(noteId,username, updatedNote));
    }

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId, @RequestParam String username) {
        noteService.deleteNote(noteId, username);
        return ResponseEntity.ok("Note deleted successfully!");
    }

}
