package server.booksnap.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import server.booksnap.domain.Book;

@Builder
public record BookResponseDto(
        @JsonProperty("bookId") Long bookId,
        @JsonProperty("title") String title,
        @JsonProperty("thumbnailUrl") String thumbnailUrl
) implements Serializable {
    public static BookResponseDto of(
            final Long bookId,
            final String title,
            final String thumbnailUrl
    ) {
        return BookResponseDto.builder()
                .bookId(bookId)
                .title(title)
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}