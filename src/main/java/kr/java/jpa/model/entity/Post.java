package kr.java.jpa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
//@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content; // columnDefinition
    // Long/long -> BIGINT
    // int -> INT
    // String -> VARCHAR

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // N:1 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false) // Unique X -> 1:N, N:1...
    private UserInfo author;

    // 연관관계 편의 메서드
    public void setAuthor(UserInfo author) {
        this.author = author;
        if (author != null) {
            author.getPosts().add(this);
        }
    }
}
