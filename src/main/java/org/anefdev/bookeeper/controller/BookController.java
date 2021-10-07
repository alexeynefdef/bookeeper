package org.anefdev.bookeeper.controller;

import lombok.Getter;
import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.exception.BookNotFoundException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequestMapping(path = "bookeeper/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService service) {
        this.bookService = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findBookById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            System.out.println(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("all")
    public List<Book> findAllBooks() {
        return bookService.getAll();
    }


    @PostMapping("add")
    public ResponseEntity<String> addBook(@RequestBody BookDTO book) {
        try {
            bookService.saveBook(book);

            return new ResponseEntity<>("ADDED NEW BOOK WITH TITLE: " + book.getTitle(), HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("find/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(bookService.findBooksByTitle(title), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("find/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(bookService.findBooksByAuthor(author), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
