package server.booksnap.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record AIRequestDto(
        @JsonProperty("text") String text
) implements Serializable {
    public static AIRequestDto of(
            final String text
    ) {
        return AIRequestDto.builder()
                .text(text)
                .build();
    }
}