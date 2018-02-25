package com.services;

import com.entities.Author;

public interface IAuthorService {

    Author save(Author author);

    long count();

    Author getByName(String name);

    void deleteAll();
}
