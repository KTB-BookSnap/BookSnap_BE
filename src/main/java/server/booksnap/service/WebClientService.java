package server.booksnap.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import server.booksnap.domain.Book;
import server.booksnap.domain.Card;
import server.booksnap.dto.request.CardRequestDto;
import server.booksnap.dto.response.CardResponseDto;
import server.booksnap.exception.CommonException;
import server.booksnap.exception.ErrorCode;
import server.booksnap.repository.BookRepository;
import server.booksnap.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final BookRepository bookRepository;
    private final CardRepository cardRepository;
    private final WebClient webClient;

    public void generateCards(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BOOK));
        List<CardResponseDto> responseList = webClient.post().uri("http://43.203.252.67/input_story")
                .bodyValue(CardRequestDto.of(
                        book.getTitle(),
                        book.getSummary()))
                .retrieve()
                .bodyToFlux(CardResponseDto.class).collectList().block();

        List<Card> list = responseList.stream().map(
                card -> Card.builder()
                        .book(book)
                        .phrase(card.phrase())
                        .imageUrl(card.imageUrl())
                        .build()).toList();

        cardRepository.saveAll(list);
        bookRepository.save(book);
    }
}