package com.table.merge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="oneforall")

public class OneForAll {

    @Id
    public int userId;

    public String username;

    public String empId;

    public String phoneNumber;

    @Column(nullable = true,unique = false)
    public String place;

    @Column(nullable = true,unique = false)
    public String altNumber;
}
