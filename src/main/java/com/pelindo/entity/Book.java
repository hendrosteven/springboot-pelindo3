package com.pelindo.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    @NotEmpty(message = "Code can't be empty")
    @Pattern(regexp = "BK[0-9]+",message = "Code must be start with BK")
    @Column(length = 10, nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Title can't be empty")
    @Column(length = 200, nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Description cant' be empty")
    @Column(length = 500)
    private String description;
    private double price;

    @NotNull
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Author author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
