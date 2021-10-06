package org.anefdev.bookeeper.controller;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "bookeeper/api")
public class BookeeperController {

    private final BookService service;

    @Autowired
    public BookeeperController(BookService service) {
        this.service = service;
    }

    public BookService getService() {
        return service;
    }

    @GetMapping
    public String greeting() {
        return "Welcome to Bookeeper API";
    }

    @GetMapping("books/{id}")
    public Book findBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping("books/add")
    public void addBook(@RequestBody BookDTO book) {
        service.saveBook(book);
    }
}
