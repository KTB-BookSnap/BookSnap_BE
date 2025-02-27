package server.booksnap.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CardResponseDto(
        @JsonProperty("title") String title,
        @JsonProperty("imageUrl") String imageUrl,
        @JsonProperty("phrase") String phrase
) implements Serializable {
    public static CardResponseDto of(
            final String title,
            final String imageUrl,
            final String phrase
    ) {
        return CardResponseDto.builder()
                .title(title)
                .imageUrl(imageUrl)
                .phrase(phrase)
                .build();
    }
}