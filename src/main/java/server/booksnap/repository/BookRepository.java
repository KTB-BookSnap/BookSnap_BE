package server.booksnap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.booksnap.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}