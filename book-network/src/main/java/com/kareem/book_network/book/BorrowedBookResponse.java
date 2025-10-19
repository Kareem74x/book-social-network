package com.kareem.book_network.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBookResponse {

    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private double rate;
    private boolean returned;
    private boolean returnApproved;


//    private String synopsis;
//    private String Owner;
//    private byte[] cover;
//    private boolean archived;
//    private boolean shareable;
}
