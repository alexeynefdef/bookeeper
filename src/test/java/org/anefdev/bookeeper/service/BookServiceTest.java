package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.dto.BookDTO;
import org.anefdev.bookeeper.dto.BookDtoConverter;
import org.anefdev.bookeeper.exception.BookAlreadyExistsException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Testing the BookService integration")
class BookServiceTest {

    @InjectMocks
    private BookService service;
    @Mock
    private BookRepository repository;

    @Test
    void getAllExistingBooks() {

        final var expectedBooks = List.of(
                new Book(
                        1L,
                        "Chapaev and void",
                        "Viktor Pelevin",
                        "Modern novel"
                ),
                new Book(
                        2L,
                        "1984",
                        "George Orwell",
                        "Distopy"
                ),
                new Book(
                        3L,
                        "Infinite Jest",
                        "David Foster Wallace",
                        "Modern novel"
                )
        );

        Mockito.when(repository.findAll()).thenReturn(expectedBooks);

        final List<BookDTO> booksFound = service.getAll();

        assertEquals(BookDtoConverter.convertListOfBooksToDTO(expectedBooks),booksFound);

    }

    @Test
    void getAllEmptyList() {

        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        var booksFound = service.getAll();

        assertTrue(booksFound.isEmpty());

    }

    @Test
    void saveBook() throws BookAlreadyExistsException {

        final var expectedBookDto = new BookDTO(
                "Chapaev and void",
                "Viktor Pelevin",
                "Modern novel"
        );

        final var expectedBook = BookDtoConverter.convertBookDTOtoBookEntity(expectedBookDto);

        Mockito.when(repository.findBooksByTitleContaining("Chapaev and void")).thenReturn(Collections.emptyList());

        service.saveBook(expectedBookDto);

        Mockito.when(repository.findBooksByTitleContaining("Chapaev and void")).thenReturn(List.of(expectedBook));

        assertEquals(BookDtoConverter.convertListOfBooksToDTO(List.of(expectedBook)),service.findBooksByTitle("Chapaev and void"));

    }

    @Test
    void saveBookAlreadyExists() {

        final var expectedBookDto = new BookDTO(
                "Chapaev and void",
                "Viktor Pelevin",
                "Modern novel"
        );

        final var expectedBook = BookDtoConverter.convertBookDTOtoBookEntity(expectedBookDto);

        Mockito.when(repository.findBooksByTitleContaining("Chapaev and void")).thenReturn(List.of(expectedBook));

        assertThrows(BookAlreadyExistsException.class,() -> service.saveBook(expectedBookDto));

    }

    @Test
    void findBooksByTitle() {
    }

    @Test
    void findBooksByAuthor() {
    }
}
