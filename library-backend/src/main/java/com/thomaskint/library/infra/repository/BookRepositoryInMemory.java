package com.thomaskint.library.infra.repository;

import com.thomaskint.library.domain.Book;
import com.thomaskint.library.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookRepositoryInMemory implements BookRepository {
    private final List<Book> data = new ArrayList<>();

    @Override
    public List<Book> all() {
        return data;
    }

    @Override
    public Optional<Book> getOne(UUID id) {
        return data.stream().filter((book) -> book.getId().equals(id)).findFirst();
    }

    @Override
    public Book save(Book book) {
        int bookIndex = data.stream()
                .filter((b) -> b.getId().equals(book.getId()))
                .findFirst()
                .map(data::indexOf)
                .orElse(-1);

        if (bookIndex > -1) {
            data.remove(bookIndex);
            data.add(bookIndex, book);
        } else {
            data.add(book);
        }

        return book;
    }
}
