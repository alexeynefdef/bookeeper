package org.anefdev.bookeeper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {

    private String title;
    private String author;
    private String description;

}

