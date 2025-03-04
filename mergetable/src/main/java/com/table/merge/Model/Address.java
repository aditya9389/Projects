package com.table.merge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="address")
public class Address {

    @Id
    public int userId;

    public String place;

    public String altNumber;
}
