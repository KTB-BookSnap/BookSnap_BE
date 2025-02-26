package server.booksnap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.booksnap.dto.global.ResponseDto;
import server.booksnap.dto.request.CardRequestDto;
import server.booksnap.service.BookService;
import server.booksnap.service.CardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookCards")
public class CardController {
    private final CardService cardService;

    @GetMapping("/{bookId}")
    public ResponseDto<?> getCardList(
        @PathVariable Long bookId
    ) {
        return ResponseDto.ok(cardService.getCardList(bookId));
    }

    @PostMapping
    public ResponseDto<?> createCardManually(
            @RequestBody CardRequestDto cardRequestDto
            ) {
        cardService.createCard(cardRequestDto);
        return ResponseDto.ok(null);
    }
}
