package com.crud.fnpblog.services;

import com.crud.fnpblog.model.Note;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.NoteRepository;
import com.crud.fnpblog.repository.UserRepository;
import com.crud.fnpblog.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service

public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public List<Note> getNotesByToken(String token)
    {
        System.out.println("------------in getNotesBytokenMethod of NoteService----------");
        String jwt=token.substring(7);
        System.out.println("-----------token given in getnotesbytoken start------->"+jwt);
        String username=jwtUtil.extractUsername(jwt);
        System.out.println("------------username extracted through jwtutil again in getnotesbytoken----->"+username);
        System.out.println("------------called getNotesByUser method of NotesService----------");
        return this.getNotesByUser(username);
    }

    public List<Note> getNotesByUser(String username) {
        System.out.println("----------in getNotesByUser method of NoteService with username------>"+username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found by this username"));
        System.out.println("---------user given by findbyuser in getnotesbyusername--------->"+user.toString());
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
        System.out.println("------------in createNote method of NoteService------------");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        note.setUser(user);
        System.out.println("------------Saving new note in database----------");
        return noteRepository.save(note);
    }

    public Note updateNote(Long noteId, String username, Note updatedNote) {
        System.out.println("------------in UpdateNote method of NoteService----------");
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to update this note");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        System.out.println("------------returning by saving new note in database----------");
        return noteRepository.save(note);
    }

    public void deleteNote(Long noteId, String username) {
        System.out.println("------------in deleteNote method of NoteService----------");
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to delete this note");
        }

        System.out.println("------------deleting specified note from database----------");
        noteRepository.deleteById(noteId);
    }
}
