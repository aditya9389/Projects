package com.crud.fnpblog.controller;

import com.crud.fnpblog.model.Note;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/getnotes")
    public ResponseEntity<Note> getNotes(@RequestBody User user) {
        return ResponseEntity.ok(noteService.getNotesByUser(user.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.loginUser(username, password);
        return user.isPresent() ? ResponseEntity.ok("Login successful!") : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.updatePassword(username, newPassword);
        return ResponseEntity.ok("Password updated successfully!");
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
