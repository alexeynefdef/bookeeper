package org.anefdev.bookeeper.dto;

import lombok.Data;

@Data
public class BookDTO {

    private String title;
    private String author;
    private String description;
    private Long rate;

}
