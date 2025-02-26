package server.booksnap.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CardRequestDto(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary
) implements Serializable {
    public static CardRequestDto of(
            final String title,
            final String summary
    ) {
        return CardRequestDto.builder()
                .title(title)
                .summary(summary)
                .build();
    }
}
