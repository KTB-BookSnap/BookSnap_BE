package server.booksnap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "summary")
    private String summary;

    @Column(name = "thumbnail_url")
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