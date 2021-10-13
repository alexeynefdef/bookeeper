package org.anefdev.bookeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {

    private String title;
    private String author;
    private String description;

}

