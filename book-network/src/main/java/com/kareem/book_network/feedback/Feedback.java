package com.kareem.book_network.feedback;

import com.kareem.book_network.book.Book;
import com.kareem.book_network.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private double rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
