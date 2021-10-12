package org.anefdev.bookeeper.service;

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

        final List<Book> booksFound = repository.findAll();

        assertEquals(expectedBooks,booksFound);
    }

    @Test
    void getAllEmptyList() {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        var booksFound = service.getAll();

        assertTrue(booksFound.isEmpty());
    }

    @Test
    void saveBook() {
    }

    @Test
    void findBooksByTitle() {
    }

    @Test
    void findBooksByAuthor() {
    }
}
