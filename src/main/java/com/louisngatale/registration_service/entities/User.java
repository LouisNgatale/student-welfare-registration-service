package com.louisngatale.registration_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(nullable=false)
    private String fullName;

    private String gender;

    @Column(nullable=false, unique=true)
    private String loginId;

    @Column(nullable=false)
    @JsonIgnore
    private String password;

    @OneToOne(mappedBy = "studentId")
    private StudentDetails studentDetails;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    referencedColumnName = "id")})
    private List<Roles> roles;
}
