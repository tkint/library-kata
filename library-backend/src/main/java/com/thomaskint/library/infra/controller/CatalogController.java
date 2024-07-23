package com.thomaskint.library.infra.controller;

import com.thomaskint.library.domain.*;
import com.thomaskint.library.domain.repository.BookRepository;
import com.thomaskint.library.domain.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "List books")
    @GetMapping(value = "books")
    List<Book> listBooks() {
        return bookRepository.all();
    }

    @Operation(summary = "Register a book into the catalog")
    @PostMapping("book")
    Book register(@RequestBody BookInput input) {
        return bookRepository.save(input.toBook());
    }

    @Operation(summary = "Borrow a book")
    @PostMapping("book/{bookId}/borrow")
    Loan borrowBook(@PathVariable UUID bookId, @RequestBody BorrowInput input) throws DomainException {
        return catalogService.borrowBook(bookId, input);
    }

    @Operation(summary = "Return a book")
    @PostMapping("book/{bookId}/return/{borrowerId}")
    void returnBook(@PathVariable UUID bookId, @PathVariable UUID borrowerId) throws DomainException {
        catalogService.returnBook(bookId, borrowerId);
    }

    @Operation(summary = "List user's currently borrowed books")
    @GetMapping("borrower/{borrowerId}/books")
    List<Book> listBorrowerBooks(@PathVariable UUID borrowerId) {
        return bookRepository.all().stream()
                .filter((book -> book.getBorrowers().contains(borrowerId)))
                .toList();
    }

    @Operation(summary = "List user's current loans")
    @GetMapping("borrower/{borrowerId}/loans")
    List<Loan> listBorrowerLoans(@PathVariable UUID borrowerId) {
        return bookRepository.all().stream()
                .filter((book -> book.getBorrowers().contains(borrowerId)))
                .flatMap((book) -> book.getLoans().stream().filter((loan) -> loan.getBorrowerId().equals(borrowerId)))
                .toList();
    }
}
