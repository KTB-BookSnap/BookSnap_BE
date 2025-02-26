package server.booksnap.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.booksnap.domain.Book;
import server.booksnap.domain.Card;
import server.booksnap.dto.request.CardRequestDto;
import server.booksnap.dto.response.CardResponseDto;
import server.booksnap.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final BookService bookService;
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

    public boolean isCardExist(CardRequestDto cardRequestDto) {
        boolean isBookExist = bookService.isBookExist(cardRequestDto.getTitle());
        //책이 이미 있을 때
        if (isBookExist) {
            Book book = bookService.getBookByTitle(cardRequestDto.getTitle());
            return cardRepository.existsByBook(book);
        }
        //책이 없을 때
        bookService.createBook(cardRequestDto.getTitle(), cardRequestDto.getSummary(), cardRequestDto.getThumbnailUrl());
        return false;
    }

    @Transactional
    public void createCard(CardRequestDto cardRequestDto) {
        if (isCardExist(cardRequestDto)) {
            return;
        }
        Book book = bookService.getBookByTitle(cardRequestDto.getTitle());

        //TODO: imageUrl, phrase 외부에 요청하는 서비스 호출 필요

        cardRepository.save(Card.builder()
                .book(book)
//                .imageUrl(cardRequestDto.getImageUrl())
//                .phrase(cardRequestDto.getPhrase())
                .build());
    }
}
