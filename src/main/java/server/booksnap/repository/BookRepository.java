package server.booksnap.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import server.booksnap.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    Optional<Book> findByTitle(String title);
}