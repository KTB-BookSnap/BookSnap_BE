package server.booksnap.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import server.booksnap.domain.Book;
import server.booksnap.dto.request.CardRequestDto;
import server.booksnap.dto.response.CardResponseDto;
import server.booksnap.exception.CommonException;
import server.booksnap.exception.ErrorCode;
import server.booksnap.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final BookRepository bookRepository;
    private final WebClient webClient;

    public List<CardResponseDto> generateCards(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BOOK));
        List<CardResponseDto> responseList = webClient.post().uri("/api/test")
                .bodyValue(CardRequestDto.of(
                        book.getTitle(),
                        book.getSummary()))
                .retrieve()
                .bodyToFlux(CardResponseDto.class).collectList().block();

        return responseList.stream().map(card -> CardResponseDto.of(book.getTitle(), card.imageUrl(), card.phrase()))
                .collect(Collectors.toList());
    }
}