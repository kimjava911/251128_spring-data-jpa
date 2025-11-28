package kr.java.jpa.model.repository;

import kr.java.jpa.model.entity.Post;
import kr.java.jpa.model.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 게시글 목록 조회 (작성자 정보 포함)
    @Query("SELECT p FROM Post p JOIN FETCH p.author ORDER BY p.createdAt DESC")
    List<Post> findAllWithAuthor();

    // 특정 사용자가 작성한 게시글 (1:N)
//    List<Post> findByAuthorId(Long authorId);
    List<Post> findByAuthorIdOrderByCreatedAtDesc(Long authorId);

    // 제목으로 검색 -> LIKE %keyword%
    List<Post> findByTitleContainingOrderByCreatedAtDesc(String keyword);
}
