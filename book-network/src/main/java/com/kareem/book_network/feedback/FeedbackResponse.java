package com.kareem.book_network.feedback;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private double rating;
    private String comment;
    private boolean ownFeedback;

}
