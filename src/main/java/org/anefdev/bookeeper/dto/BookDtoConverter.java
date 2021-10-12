package org.anefdev.bookeeper.dto;

import org.anefdev.bookeeper.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookDtoConverter {

    public static List<BookDTO> convertEntitiesToDTO(List<Book> bookEntities) {

        if (!bookEntities.isEmpty()) {

            return bookEntities.stream().map(book -> BookDTO.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .description(book.getDescription())
                    .build()
            ).collect(Collectors.toList());

        } else return Collections.emptyList();

    }

}
