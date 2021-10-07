package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.exception.BookNotFoundException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookRepository getRepository() {
        return repository;
    }

    public Book getBookById(long id) throws BookNotFoundException {
        Optional<Book> book = repository.findById(id);
        return book.orElseThrow(() -> new BookNotFoundException("BOOK WITH ID: " + id + " NOT FOUND"));
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public void saveBook(BookDTO bookDto) throws BookAlreadyExistsException {

        if (repository.findByTitleContainingIgnoreCase(bookDto.getTitle()).isEmpty()) {

            var newBook = Book.builder()
                    .title(bookDto.getTitle())
                    .author(bookDto.getAuthor())
                    .description(bookDto.getDescription())
                    .rate(bookDto.getRate())
                    .build();

            repository.save(newBook);

        } else {
            throw new BookAlreadyExistsException("BOOK WITH TITLE: " + bookDto.getTitle() + " ALREADY EXISTS");
        }
    }

    public List<Book> findBooksByTitle(String title) throws BookNotFoundException {

        var booksFound = repository.findByTitleContainingIgnoreCase(title);

        if (!booksFound.isEmpty()) {
            return booksFound;
        } else {
            throw new BookNotFoundException("BOOK WITH TITLE: " + title + " NOT FOUND");
        }
    }

    public List<Book> findBooksByAuthor(String author) throws BookNotFoundException {

        var booksFound = repository.findByTitleContainingIgnoreCase(author);

        if (!booksFound.isEmpty()) {
            return booksFound;
        } else {
            throw new BookNotFoundException("BOOKS BY: " + author + " ARE NOT FOUND");
        }
    }
}
