package com.services;

import com.daos.AuthorRepository;
import com.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }
}
