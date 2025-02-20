package com.crud.fnpblog.repository;

import com.crud.fnpblog.model.Note;
import com.crud.fnpblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
