package kr.java.jpa.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.jpa.model.entity.UserInfo;
import kr.java.jpa.service.PostService;
import kr.java.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public String postList(HttpSession session, Model model) {
        UserInfo loginUser = (UserInfo) session.getAttribute("userInfo");
        if (loginUser == null) return "redirect:/login";
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("userInfo", loginUser);
        return "post/list"; // webapp/WEB-INF/views/post/list.jsp
    }

    @GetMapping("/new")
    public String createForm(
            HttpSession session
    ) {
        if (session.getAttribute("userInfo") == null) return "redirect:/login";
        return "post/form";
    }

    @PostMapping("/new")
    public String create(
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session
    ) {
        UserInfo loginUser = (UserInfo) session.getAttribute("userInfo");
        if (loginUser == null) return "redirect:/login";
        postService.createPost(title, content, loginUser.getId());
        return "redirect:/posts";
    }
}
