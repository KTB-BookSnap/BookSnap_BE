package server.booksnap.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import server.booksnap.domain.Book;
import server.booksnap.domain.Card;
import server.booksnap.dto.request.AIRequestDto;
import server.booksnap.dto.request.CardRequestDto;
import server.booksnap.dto.response.CardResponseDto;
import server.booksnap.exception.CommonException;
import server.booksnap.exception.ErrorCode;
import server.booksnap.repository.BookRepository;
import server.booksnap.repository.CardRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientService {

    private final BookRepository bookRepository;
    private final CardRepository cardRepository;
    private final WebClient webClient;

    @Transactional
    public void generateCards(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BOOK));

        Map<String, Map<String, CardResponseDto>> responseMap = webClient.post()
                .uri("http://43.203.252.67:8000/input_story")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(AIRequestDto.of(book.getSummary()))  // AIRequestDto는 필요에 맞게 설정
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Map<String, CardResponseDto>>>() {})
                .block();

        Map<String, CardResponseDto> cardMap = responseMap.get("result");
        List<CardResponseDto> responseList = cardMap.values().stream().toList();

        List<Card> list = responseList.stream().map(
                card -> Card.builder()
                        .book(book)
                        .phrase(card.text())
                        .imageUrl(card.image())
                        .build()).toList();

        cardRepository.saveAll(list);

        // 카드가 존재하면 첫 번째 카드의 imageUrl을 책의 thumbnailUrl로 지정
        if (!list.isEmpty()) {
            book.setThumbnailUrl(list.get(0).getImageUrl());
        }

        bookRepository.save(book);
    }
}