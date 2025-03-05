package com.table.merge.Model;

import com.table.merge.eventlistner.EntityChangeListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(EntityChangeListener.class)
@Getter
@Setter
@Table(name="address")
public class Address {

    @Id
    public int userId;

    public String place;

    public String altNumber;
}
