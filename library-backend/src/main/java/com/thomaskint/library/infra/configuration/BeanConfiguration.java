package com.thomaskint.library.infra.configuration;

import com.thomaskint.library.domain.repository.BookRepository;
import com.thomaskint.library.domain.service.CatalogService;
import com.thomaskint.library.domain.service.CatalogServiceImpl;
import com.thomaskint.library.infra.repository.BookRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    BookRepository bookRepository() {
        return new BookRepositoryInMemory();
    }

    @Bean
    CatalogService catalogService(BookRepository bookRepository) {
        return new CatalogServiceImpl(bookRepository);
    }
}
