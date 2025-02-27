package server.booksnap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Lob // 긴 문자열 저장을 위한 어노테이션
    @Column(name = "summary", columnDefinition = "TEXT") // DB에서 TEXT 타입 사용 (선택적)
    private String summary;

    @Column(name = "thumbnail_url")
    @Setter
    private String thumbnailUrl;

    @Builder
    public Book(
            final String title,
            final String summary,
            final String thumbnailUrl
    ) {
        this.title = title;
        this.summary = summary;
        this.thumbnailUrl = thumbnailUrl;
    }
}