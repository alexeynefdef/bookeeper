package org.anefdev.bookeeper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequestMapping(path = "bookeeper/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("all")
    public ResponseEntity<List<Book>> findAllBooks() {
        var headers = new HttpHeaders();
        headers.set("Content-type","application/json");
        headers.set("Access-Control-Allow-Origin","*");
        return ResponseEntity.ok().headers(headers).body(bookService.getAll());
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

        return new ResponseEntity<>(bookService.findBooksByTitle(title), HttpStatus.OK);

    }

    @GetMapping("find/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
