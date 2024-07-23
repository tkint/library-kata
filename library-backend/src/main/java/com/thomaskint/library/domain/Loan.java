package com.thomaskint.library.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private UUID id;
    private UUID borrowerId;
    private BorrowedBook book;
    private LocalDate startDate;
    private int days;

    public Loan(UUID id, UUID borrowerId, Book book, LocalDate startDate, int days) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.book = new BorrowedBook(book);
        this.startDate = startDate;
        this.days = days;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public BorrowedBook getBook() {
        return book;
    }

    public void setBook(BorrowedBook book) {
        this.book = book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
