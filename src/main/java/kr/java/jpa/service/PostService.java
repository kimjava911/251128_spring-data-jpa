package kr.java.jpa.service;

import kr.java.jpa.model.entity.Post;
import kr.java.jpa.model.entity.UserInfo;
import kr.java.jpa.model.entity.UserLogin;
import kr.java.jpa.model.repository.PostRepository;
import kr.java.jpa.model.repository.UserInfoRepository;
import kr.java.jpa.model.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 필수 생성자를 자동으로 만들어지게
@Transactional(readOnly = true) // 쓰기가 아닌 transaction을 기본으로
public class PostService {
    private final UserInfoRepository userInfoRepository;
    private final PostRepository postRepository;

//    게시글 목록 조회 (N:1)
    public List<Post> getAllPosts() {
//        return postRepository.findAll();
        return postRepository.findAllWithAuthor(); // N+1 문제 방지를 위한 코딩
    }

    // 게시글 작성 (1:N)
    @Transactional
    public Long createPost(String title, String content, Long authorId) {
        UserInfo author = userInfoRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        post.setAuthor(author); // 연관관계 편의 메서드
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
}
