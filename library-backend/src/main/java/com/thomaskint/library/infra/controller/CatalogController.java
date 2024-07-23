package com.thomaskint.library.infra.controller;

import com.thomaskint.library.domain.*;
import com.thomaskint.library.domain.repository.BookRepository;
import com.thomaskint.library.domain.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("catalog")
public class CatalogController {
    private final BookRepository bookRepository;
    private final CatalogService catalogService;

    public CatalogController(BookRepository bookRepository, CatalogService catalogService) {
        this.bookRepository = bookRepository;
        this.catalogService = catalogService;
    }

    @GetMapping("books")
    List<Book> listBooks() {
        return bookRepository.all();
    }

    @PostMapping("book")
    Book register(@RequestBody BookInput input) {
        return bookRepository.save(input.toBook());
    }

    @PostMapping("book/{bookId}/borrow")
    Loan borrowBook(@PathVariable UUID bookId, @RequestBody BorrowInput input) throws DomainException {
        return catalogService.borrowBook(bookId, input);
    }

    @PostMapping("book/{bookId}/return/{borrowerId}")
    void returnBook(@PathVariable UUID bookId, @PathVariable UUID borrowerId) throws DomainException {
        catalogService.returnBook(bookId, borrowerId);
    }

    @GetMapping("borrower/{borrowerId}/books")
    List<Book> listBorrowerBooks(@PathVariable UUID borrowerId) {
        return bookRepository.all().stream()
                .filter((book -> book.getBorrowers().contains(borrowerId)))
                .toList();
    }

    @GetMapping("borrower/{borrowerId}/loans")
    List<Loan> listBorrowerLoans(@PathVariable UUID borrowerId) {
        return bookRepository.all().stream()
                .filter((book -> book.getBorrowers().contains(borrowerId)))
                .flatMap((book) -> book.getLoans().stream().filter((loan) -> loan.getBorrowerId().equals(borrowerId)))
                .toList();
    }
}
