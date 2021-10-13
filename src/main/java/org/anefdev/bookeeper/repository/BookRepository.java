package org.anefdev.bookeeper.repository;

import org.anefdev.bookeeper.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByTitleContaining(String title);
    List<Book> findBooksByAuthorContaining(String author);
}
