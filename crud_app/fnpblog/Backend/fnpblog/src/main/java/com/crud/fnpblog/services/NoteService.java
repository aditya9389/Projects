package com.crud.fnpblog.services;

import com.crud.fnpblog.model.Note;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.NoteRepository;
import com.crud.fnpblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getNotesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found by this username"));
        return noteRepository.findByUser(user);
    }
    public List<Note> getNotesByUserId(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found by this userid"));
        return noteRepository.findByUser(user);
    }
    public List<Note> getNotesByUserEmpId(String userEmpId) {
        User user = userRepository.findByEmpId(userEmpId)
                .orElseThrow(() -> new RuntimeException("User not found by this userEmpId"));
        return noteRepository.findByUser(user);
    }

    public Note createNote(String username, Note note) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        note.setUser(user);
        return noteRepository.save(note);
    }

    public Note updateNote(Long noteId, String username, Note updatedNote) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to update this note");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        return noteRepository.save(note);
    }

    public void deleteNote(Long noteId, String username) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to delete this note");
        }

        noteRepository.deleteById(noteId);
    }
}
