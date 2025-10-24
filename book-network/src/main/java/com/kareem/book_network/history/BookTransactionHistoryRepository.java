package com.kareem.book_network.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
        SELECT h
        FROM BookTransactionHistory h
        WHERE h.user.id = :userId
    """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);



    @Query("""
        SELECT h
        FROM BookTransactionHistory h
        WHERE h.book.owner.id = :userId
    """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);



    @Query("""
        SELECT
        (COUNT(*) > 0) AS isBorrowed
        FROM BookTransactionHistory h
        where h.user.id = :userId
        AND h.book.id = :bookId
        AND h.returnApproved = false
    """)
    boolean isAlreadyBorrowedByUser(Integer bookId, Integer userId);



    @Query("""
        SELECT t
        FROM BookTransactionHistory t
        where t.user.id = :userId
        AND t.book.id = :bookId
        AND t.returned = false
        AND t.returnApproved = false
    """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId, Integer userId);
}
