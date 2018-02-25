package com.services;

import com.daos.BookRepository;
import com.entities.Author;
import com.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BookService implements IBookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByName(String name) {
        return bookRepository.getBookByName(name);
    }

    @Override
    public void delete(List<Book> booksToDelete) {
        bookRepository.delete(booksToDelete);
    }

    @Override
    public void delete(Book bookToDelete) {
        bookRepository.delete(bookToDelete);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> saveTwoBooksWithNames(String name1, String name2) {
        Book b1 = new Book(name1);
        Book b2 = new Book(name2);

        return Arrays.asList(bookRepository.save(b1), bookRepository.save(b2));

    }

    @Transactional
    public List<Book> saveTwoBooksWithNamesWithAuthorWithName(String name1, String name2, String nameAuthor) {
        Book b1 = new Book(name1);
        Book b2 = new Book(name2);
        Author author = authorService.getByName(nameAuthor);

        b1.setAuthor(author);
        b2.setAuthor(author);

        return Arrays.asList(bookRepository.save(b1), bookRepository.save(b2));

    }

    @Override
    public void renameBookWithoutExplicitSaveAndWithoutTransactionnal(String name1, String name2) {
        Book b = findByName(name1);
        b.setName(name2);
    }

    @Override
    @Transactional
    public void renameBookWithoutExplicitSaveAndWithTransactionnal(String name1, String name2) {
        Book b = findByName(name1);
        b.setName(name2);
    }

    public Book saveThenRenameArgEntity(Book book, String rename) {
        bookRepository.save(book);
        book.setName(rename);
        return book;
    }


    public Book saveThenRenameReturnedEntity(Book book, String rename) {
        book = bookRepository.save(book);
        book.setName(rename);
        return book;
    }

    @Override
    @Transactional
    public Book saveThenRenameArgEntityWithTransaction(Book book, String rename) {
        bookRepository.save(book);
        book.setName(rename);
        return book;
    }

    @Override
    @Transactional
    public Book saveThenRenameReturnedEntityWithTransaction(Book book, String rename) {
        book = bookRepository.save(book);
        book.setName(rename);
        return book;
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    @Transactional
    public void updateBookAuthorName(String aze1, String nameAuthor) {
        Book bookUpdated = bookRepository.getBookByName(aze1);
        Author authorReassigned = authorService.getByName(nameAuthor);
        bookUpdated.setAuthor(authorReassigned);
    }

    @Override
    @Transactional
    public void saveAuthorAndBooks(String nameAuthor, String b1, String b2) {
        Author a = new Author(nameAuthor);
        a = authorService.save(a);
        Book book1 = new Book(b1);
        Book book2 = new Book(b2);

        book1.setAuthor(a);
        save(book1);
        book2 = save(book2);
        book2.setAuthor(a);
    }

}
