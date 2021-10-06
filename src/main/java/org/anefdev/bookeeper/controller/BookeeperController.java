package org.anefdev.bookeeper.controller;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.service.BookService;
import org.anefdev.bookeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "bookeeper/api")
public class BookeeperController {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookeeperController(BookService service, UserService userService) {
        this.bookService = service;
        this.userService = userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    @GetMapping
    public String greeting() {
        return "Welcome to Bookeeper API";
    }

    @GetMapping("books/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("users/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("books/all")
    public List<Book> findAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("users/all")
    public List<User> findAllUsers() {
        return userService.getAll();
    }


    @PostMapping("books/add")
    public String addBook(@RequestBody BookDTO book) {
        bookService.saveBook(book);
        return "ADDED NEW BOOK";
    }

    @PostMapping("users/add")
    public String addUser(@RequestBody UserDTO user) {
        userService.saveUser(user);
        return "ADDED NEW USER";
    }
}
