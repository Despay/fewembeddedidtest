package com.services;

import com.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBookService {


    long count();

    List<Book> findAll();

    Book findByName(String name);

    void delete(List<Book> booksToDelete);

    void delete(Book bookToDelete);

    Book save(Book book);

    List<Book> saveTwoBooksWithNames(String name1, String name2);

    List<Book> saveTwoBooksWithNamesWithAuthorWithName(String name1, String name2, String nameAuthor);

    void renameBookWithoutExplicitSaveAndWithoutTransactionnal(String name1, String name2);

    void renameBookWithoutExplicitSaveAndWithTransactionnal(String name1, String name2);

    Book saveThenRenameArgEntity(Book book, String rename);

    Book saveThenRenameReturnedEntity(Book book, String rename);

    Book saveThenRenameArgEntityWithTransaction(Book book, String rename);

    Book saveThenRenameReturnedEntityWithTransaction(Book book, String rename);

    void deleteAll();

    void updateBookAuthorName(String aze1, String nameAuthor);

    void saveAuthorAndBooks(String nameAuthor, String b1, String b2);

}
