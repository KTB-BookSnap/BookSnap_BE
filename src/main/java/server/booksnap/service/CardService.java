package server.booksnap.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.booksnap.dto.response.CardResponseDto;
import server.booksnap.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public List<CardResponseDto> getCardList(Long bookId) {
        return cardRepository.findAllByBookId(bookId).stream()
                .map(card -> CardResponseDto.of(
                        card.getBook().getTitle(),
                        card.getImageUrl(),
                        card.getPhrase()
                )).collect(Collectors.toList());
    }
}
