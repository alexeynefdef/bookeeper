package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Book getBookById(Long id) {
        return repository.getById(id);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public String saveBook(BookDTO bookDto) {
        var newBook = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .description(bookDto.getDescription())
                .rate(bookDto.getRate())
                .build();

        repository.save(newBook);
        return "ADDED NEW BOOK";
    }
}
