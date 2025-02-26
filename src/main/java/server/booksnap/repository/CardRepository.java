package server.booksnap.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import server.booksnap.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByBookId(Long bookId);
}