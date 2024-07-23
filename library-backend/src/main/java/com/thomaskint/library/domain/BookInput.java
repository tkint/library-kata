package com.thomaskint.library.domain;

import com.thomaskint.library.domain.repository.BookRepository;

import java.util.UUID;

public record BookInput(
        String title,
        int stock
) {
    public Book toBook() {
        return new Book(UUID.randomUUID(), title, stock);
    }
}
