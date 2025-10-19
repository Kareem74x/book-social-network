package com.kareem.book_network.book;

import com.kareem.book_network.common.PageResponse;
import com.kareem.book_network.history.BookTransactionHistory;
import com.kareem.book_network.history.BookTransactionHistoryRepository;
import com.kareem.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;


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

    public PageResponse<BookResponse> findAllBooks(int pageNumber, int size, Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("createdDate").descending());

        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }


    public PageResponse<BookResponse> findAllBooksByOwner(int pageNumber, int size, Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("createdDate").descending());

        Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int pageNumber, int size, Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("createdDate").descending());

        Page<BookTransactionHistory> borrowedBooks = bookTransactionHistoryRepository.findAllBorrowedBooks(pageable, user.getId());

        List<BorrowedBookResponse> borrowedBookResponses = borrowedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<>(
                borrowedBookResponses,
                borrowedBooks.getNumber(),
                borrowedBooks.getSize(),
                borrowedBooks.getTotalElements(),
                borrowedBooks.getTotalPages(),
                borrowedBooks.isFirst(),
                borrowedBooks.isLast()
        )
    }
}
