package com.thomaskint.library.domain.service;

import com.thomaskint.library.domain.BorrowInput;
import com.thomaskint.library.domain.DomainException;
import com.thomaskint.library.domain.Loan;

import java.util.UUID;

public interface CatalogService {
    Loan borrowBook(UUID bookId, BorrowInput input) throws DomainException;

    void returnBook(UUID bookId, UUID borrowerId) throws DomainException;
}
