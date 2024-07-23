package com.thomaskint.library.domain.repository;

import com.thomaskint.library.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {
    List<Book> all();

    Optional<Book> getOne(UUID id);

    Book save(Book book);
}
