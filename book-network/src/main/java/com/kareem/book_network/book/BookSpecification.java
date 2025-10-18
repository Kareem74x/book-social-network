package com.kareem.book_network.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(Integer ownerId) {
        // This is a lambda expression implementing the toPredicate(...) method from the Specification interface.


        // root → represents the entity (Book) being queried.

        // query → represents the full JPA query being built.
        // You usually don’t modify it unless you want to set sorting, fetch joins, etc.

        // criteriaBuilder → helps you build SQL-like conditions (equal, like, greaterThan, etc.)


        // criteriaBuilder.equal(root.get("owner").get("id"), ownerId) ====>  WHERE owner.id = :ownerId
        // So the whole method builds a specification equivalent to ====> SELECT * FROM books b WHERE b.owner_id = :ownerId

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }

}
