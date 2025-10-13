package com.kareem.book_network.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(

        Integer id,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Author name is required")
        String authorName,

        @NotBlank(message = "isbn is required")
        String isbn,

        @NotBlank(message = "Synopsis is required")
        String synopsis,

        boolean shareable
) {
}
