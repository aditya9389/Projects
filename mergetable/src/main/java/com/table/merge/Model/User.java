package com.table.merge.Model;

import com.table.merge.eventlistner.EntityChangeListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(EntityChangeListener.class)
@Getter
@Setter
@Table(name="user")

public class User {

    @Id
    public int userId;

    @Column(nullable = false,unique = true)
    public String username;

    @Column(nullable = false,unique = true)
    public String empId;

    @Column(nullable = false,unique = true)
    public String phoneNumber;

}
