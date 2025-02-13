package com.crud.fnpblog.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String empId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String blogTitle;

    @Column(columnDefinition = "TEXT")
    private String blogContent;
}
