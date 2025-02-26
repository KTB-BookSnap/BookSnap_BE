package server.booksnap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.booksnap.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}