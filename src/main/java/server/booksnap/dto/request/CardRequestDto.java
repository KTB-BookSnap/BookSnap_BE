package server.booksnap.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Builder
public record CardRequestDto(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("thumbnailUrl") String thumbnailUrl
) implements Serializable {
    public static CardRequestDto of(
            final String title,
            final String summary,
            final String thumbnailUrl
    ) {
        return CardRequestDto.builder()
                .title(title)
                .summary(summary)
                .thumbnailUrl(thumbnailUrl)
                .build();
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
}
