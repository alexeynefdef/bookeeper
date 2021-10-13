package org.anefdev.bookeeper.dto;

import org.anefdev.bookeeper.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookDtoConverter {


    public static List<BookDTO> convertListOfBooksToDTO(List<Book> books) {

        if (!books.isEmpty()) {

            return books.stream().map(book -> BookDTO.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .description(book.getDescription())
                    .build()
            ).collect(Collectors.toList());

        } else return Collections.emptyList();

    }

    public static Book convertBookDTOtoBookEntity(BookDTO bookDTO) {

        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .build();

    }

    /*public static BookDTO convertBookToDTO(Book book) {

        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .build();

    }*/

}
