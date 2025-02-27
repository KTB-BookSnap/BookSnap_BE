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
import server.booksnap.exception.CommonException;
import server.booksnap.exception.ErrorCode;
import server.booksnap.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final BookService bookService;
    private final CardRepository cardRepository;
    private final WebClientService webClientService;

    @Transactional(readOnly = true)
    public List<CardResponseDto> getCardList(Long bookId) {
        return cardRepository.findAllByBookId(bookId).stream()
                .map(card -> CardResponseDto.of(
                        card.getBook().getTitle(),
                        card.getImageUrl(),
                        card.getPhrase()
                )).collect(Collectors.toList());
    }

    /**
     * 카드가 이미 존재하는지 확인
     */
    public boolean isCardExist(CardRequestDto cardRequestDto) {
        boolean isBookExist = bookService.isBookExist(cardRequestDto.getTitle());
        //책이 이미 있을 때
        if (isBookExist) {
            Book book = bookService.getBookByTitle(cardRequestDto.getTitle());
            return cardRepository.existsByBook(book);
        }
        //책이 없을 때
        bookService.createBook(cardRequestDto.getTitle(), cardRequestDto.getSummary());
        return false;
    }

    public void createCard(CardRequestDto cardRequestDto) {
        if (isCardExist(cardRequestDto)) {
            throw new CommonException(ErrorCode.DUPLICATED_BOOK);
        }
        Book book = bookService.getBookByTitle(cardRequestDto.getTitle());

        webClientService.generateCards(book.getId());
    }
}