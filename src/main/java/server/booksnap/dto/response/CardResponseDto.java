package server.booksnap.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CardResponseDto(
        @JsonProperty("title") String title,
        @JsonProperty("image") String image,
        @JsonProperty("text") String text
) implements Serializable {
    public static CardResponseDto of(
            final String title,
            final String image,
            final String text
    ) {
        return CardResponseDto.builder()
                .title(title)
                .image(image)
                .text(text)
                .build();
    }
}