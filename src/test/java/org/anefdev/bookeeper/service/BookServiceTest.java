package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.exception.BookNotFoundException;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Testing the BookService integration")
class BookServiceTest {

    private BookService service;
    @Mock
    private BookRepository repository;

    @BeforeEach
    void init() {
        service = new BookService(repository);
    }

    @Test
    void getBookByIdExists() {
        var expectedEntity = new Book(
                "1984",
                "George Orwell",
                "Dystopia"
        );

        Mockito.when(repository.getById(1L)).thenReturn(expectedEntity);

        try {
            var book = service.getBookById(1L);
            assertEquals(expectedEntity,book);
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBookByIdNotExists() {
        var expectedEntity = new Book(
                "1984",
                "George Orwell",
                "Dystopia"
        );

        Mockito.when(repository.getById(1L)).thenReturn(expectedEntity);

        assertThrows(BookNotFoundException.class,() -> service.getBookById(2L));
    }


    @Test
    void getAllExistingBooks() {
        final var expectedBooks = List.of(
                new Book(
                        (long)1,
                        "Chapaev and void",
                        "Viktor Pelevin",
                        "Modern novel",
                        (long)5,
                        Collections.emptyList()
                ),
                new Book(
                        (long)2,
                        "1984",
                        "George Orwell",
                        "Distopy",
                        (long)5,
                        Collections.emptyList()
                ),
                new Book(
                        (long)3,
                        "Infinite Jest",
                        "David Foster Wallace",
                        "Modern novel",
                        (long)5,
                        Collections.emptyList()
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
