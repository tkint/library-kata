package com.thomaskint.library.domain;

import java.util.UUID;

public class BorrowedBook {
    private final UUID id;
    private final String title;

    public BorrowedBook(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
