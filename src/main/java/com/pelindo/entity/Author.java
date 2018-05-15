package com.pelindo.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_author")
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    @NotBlank(message = "Name can't empty")
    @Size(min=2, message="Name should have min 2 characters")
    @Column(length = 150, nullable = false)
    private
    String name;

    @NotBlank(message = "Name can't empty")
    @Email(message = "Invalid email address")
    @Column(length = 200, unique = true)
    private
    String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
