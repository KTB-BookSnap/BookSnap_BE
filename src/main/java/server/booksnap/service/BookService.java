package server.booksnap.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.booksnap.domain.Book;
import server.booksnap.dto.response.BookResponseDto;
import server.booksnap.exception.CommonException;
import server.booksnap.exception.ErrorCode;
import server.booksnap.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAll().stream()
                .map(book -> BookResponseDto.of(
                        book.getId(),
                        book.getTitle(),
                        book.getThumbnailUrl()
                )).collect(Collectors.toList());
    }

    public String getBookTitle(Long bookId) {
        return bookRepository.findById(bookId)
                .map(Book::getTitle)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BOOK));
    }
}