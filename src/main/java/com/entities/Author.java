package com.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author extends AbstractEntity {


    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
