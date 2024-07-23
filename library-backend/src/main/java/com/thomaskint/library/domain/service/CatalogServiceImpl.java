package com.thomaskint.library.domain.service;

import com.thomaskint.library.domain.Book;
import com.thomaskint.library.domain.BorrowInput;
import com.thomaskint.library.domain.DomainException;
import com.thomaskint.library.domain.DomainException.ErrorCode;
import com.thomaskint.library.domain.Loan;
import com.thomaskint.library.domain.repository.BookRepository;

import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    private final BookRepository bookRepository;

    public CatalogServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Loan borrowBook(UUID bookId, BorrowInput input) throws DomainException {
        Book book = getBook(bookId);
        Loan loan = book.borrowIt(input.borrowerId(), input.days());
        bookRepository.save(book);
        return loan;
    }

    @Override
    public void returnBook(UUID bookId, UUID borrowerId) throws DomainException {
        Book book = getBook(bookId);
        book.returnIt(borrowerId);
        bookRepository.save(book);
    }

    private Book getBook(UUID id) throws DomainException {
        return bookRepository.getOne(id)
                .orElseThrow(() -> new DomainException(ErrorCode.BOOK_NOT_FOUND, "Book %s not found"));
    }
}
