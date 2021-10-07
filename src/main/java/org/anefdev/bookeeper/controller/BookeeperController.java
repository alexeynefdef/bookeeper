package org.anefdev.bookeeper.controller;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.exception.BookNotFoundException;
import org.anefdev.bookeeper.exception.UserAlreadyExistsException;
import org.anefdev.bookeeper.exception.UserNotFoundException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.service.BookService;
import org.anefdev.bookeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Book> findBookById(@PathVariable long id) {
        try {

            return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
        } catch (BookNotFoundException e) {
            System.out.println(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<String> addBook(@RequestBody BookDTO book) {

        try {
            bookService.saveBook(book);

            return new ResponseEntity<>("ADDED NEW BOOK WITH TITLE: " + book.getTitle(),HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("users/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {

        try {
            userService.saveUser(user);

            return new ResponseEntity<>(
                    "ADDED NEW USER: " + user.getUsername(),
                    HttpStatus.CREATED
            );
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("books/find/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {

        try {
            return new ResponseEntity<>(bookService.findBooksByTitle(title),HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
