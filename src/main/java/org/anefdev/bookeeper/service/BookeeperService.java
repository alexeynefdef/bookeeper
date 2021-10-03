package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookeeperService {

    private final BookRepository repository;

    @Autowired
    public BookeeperService(BookRepository repository) {
        this.repository = repository;
    }

    public BookRepository getRepository() {
        return repository;
    }

    public Book getBookById(Long id) {
        return repository.getById(id);
    }

    public void saveBook(BookDTO bookDto) {
        var newBook = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .description(bookDto.getDescription())
                .rate(bookDto.getRate())
                .build();

        repository.save(newBook);
    }
}
