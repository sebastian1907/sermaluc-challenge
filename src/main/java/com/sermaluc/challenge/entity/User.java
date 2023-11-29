package com.sermaluc.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@Entity
@Table(name="PERSON")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column( name = "NAME")
    private String name;
    @Column( name = "EMAIL" , unique = true)
    private String email;
    @Column( name = "PASSWORD")
    private String password;
    @OneToMany( targetEntity = Phone.class)
    private Collection<Phone> phones = new ArrayList<>();
    @Column( name = "CREATED")
    private LocalDateTime created;
    @Column( name = "MODIFIED")
    private LocalDateTime modified;
    @Column( name = "LAST_LOGIN")
    private LocalDateTime last_login;
    @Column(name = "ACTIVE")
    private Boolean isActive = Boolean.TRUE;
    @Column(name = "TOKEN")
    private String token;

    public User(String name , String email, String password, String token,Collection<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password =password;
        this.phones = phones;
        this.token = token;
    }

    @PrePersist
    public void setCreated() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.created = localDateTime;
        setLast_login(localDateTime);
    }

    @PreUpdate
    public void setModified() {
        this.modified = LocalDateTime.now();
    }
}
