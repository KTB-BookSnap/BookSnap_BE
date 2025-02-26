package server.booksnap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.booksnap.dto.global.ResponseDto;
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
}
