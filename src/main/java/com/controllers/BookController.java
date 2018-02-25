package com.controllers;

import com.entities.Book;
import com.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    List<Book> getAll() {
        return bookService.findAll();
    }

}
