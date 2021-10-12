package org.anefdev.bookeeper.service;

import lombok.RequiredArgsConstructor;
import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.dto.BookDtoConverter;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository repository;

    public List<BookDTO> getAll() {

        return BookDtoConverter.convertEntitiesToDTO(repository.findAll());

    }

    public void saveBook(BookDTO bookDto) throws BookAlreadyExistsException {

        if (repository.findBooksByTitleContaining(bookDto.getTitle()).isEmpty()) {

            var newBook = Book.builder()
                    .title(bookDto.getTitle())
                    .author(bookDto.getAuthor())
                    .description(bookDto.getDescription())
                    .build();

            repository.save(newBook);

        } else {
            throw new BookAlreadyExistsException("BOOK WITH TITLE: " + bookDto.getTitle() + " ALREADY EXISTS");
        }
    }

    public List<BookDTO> findBooksByTitle(String title) {

        return BookDtoConverter.convertEntitiesToDTO(repository.findBooksByTitleContaining(title));

    }

    public List<BookDTO> findBooksByAuthor(String author) {

        return BookDtoConverter.convertEntitiesToDTO(repository.findBooksByAuthorContaining(author));

    }

}
