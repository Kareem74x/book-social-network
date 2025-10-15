package com.kareem.book_network.book;

import com.kareem.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;


    public Integer save(BookRequest request, Authentication connectedUser) {

        Book book = bookMapper.toBook(request);

        User user = ((User) connectedUser.getPrincipal());
        book.setOwner(user);

        Book savedBook =  bookRepository.save(book);
        return savedBook.getId();
        //return bookRepository.save(book).getId();
    }


    public BookResponse findById(Integer bookId) {

        return bookRepository.findById(bookId)
                //.map(book -> bookMapper.toBookResponse(book))
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));
    }
}
