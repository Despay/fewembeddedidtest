package com.entities;

import javax.persistence.*;

@Entity
public class Book extends AbstractEntity {


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "author_id", referencedColumnName = "id"),
            @JoinColumn(name = "env_id", referencedColumnName = "env_id", insertable = false, updatable = false)
    })
    private Author author;

    @Column(name = "author_ifffdk")
    private Long authorFk;

    private String name;

    public Book() {
    }


    public Book(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        /*if(author == null) {
            authorFk = null;
        } else {
            authorFk = author.getId().getId();
        }*/
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
