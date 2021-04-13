package com.louisngatale.registration_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Roles {

    @Id
    private String id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
