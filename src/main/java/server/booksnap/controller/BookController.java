package server.booksnap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.booksnap.dto.global.ResponseDto;
import server.booksnap.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseDto<?> getBookList() {
        return ResponseDto.ok(bookService.getBookList());
    }
}