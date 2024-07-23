package com.thomaskint.library.domain;

import com.thomaskint.library.domain.DomainException.ErrorCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private int stock;
    private List<Loan> loans;

    public Book(UUID id, String title, int stock) {
        this.id = id;
        this.title = title;
        this.stock = stock;
        this.loans = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStock() {
        return stock;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public int getCurrentStock() {
        return stock - loans.size();
    }

    public boolean isAvailable() {
        return getCurrentStock() > 0;
    }

    public Loan borrowIt(UUID borrowerId, int days) throws DomainException {
        Optional<Loan> existingLoan = getBorrowerLoan(borrowerId);
        if (existingLoan.isPresent()) {
            throw new DomainException(ErrorCode.EXISTING_LOAN, "Book already borrowed by this user");
        }

        Loan loan = new Loan(UUID.randomUUID(), borrowerId, this, LocalDate.now(), days);
        this.loans.add(loan);

        return loan;
    }

    public void returnIt(UUID borrowerId) throws DomainException {
        Loan existingLoan = getBorrowerLoan(borrowerId)
                .orElseThrow(() -> new DomainException(ErrorCode.LOAN_NOT_FOUND, "No loan found for %s".formatted(borrowerId)));
        this.loans.remove(existingLoan);
    }

    public List<UUID> getBorrowers() {
        return loans.stream()
                .map(Loan::getBorrowerId)
                .toList();
    }

    private Optional<Loan> getBorrowerLoan(UUID borrowerId) {
        return loans.stream()
                .filter((loan) -> loan.getBorrowerId().equals(borrowerId))
                .findFirst();
    }
}
