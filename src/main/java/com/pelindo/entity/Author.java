package com.pelindo.entity;


import javax.persistence.*;

@Entity
@Table(name = "tbl_author")
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;
    @Column(length = 150, nullable = false)
    private
    String name;
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
