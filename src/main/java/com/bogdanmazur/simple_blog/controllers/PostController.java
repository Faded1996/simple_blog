package com.bogdanmazur.simple_blog.controllers;

import com.bogdanmazur.simple_blog.entity.Post;
import com.bogdanmazur.simple_blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);
        return "all-posts";
    }

    @GetMapping("/posts/add")
    public String addNewPost(Model model) {
        Post post = new Post();
        model.addAttribute("newPost", post);
        return "add-new-post";
    }

    @PostMapping("/posts/add")
    public String saveNewPost(@ModelAttribute("newPost") @Valid Post post,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-new-post";
        }
        postService.saveNewPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable(value = "id") Long id,
                              Model model) {
        Post postById = postService.getPostById(id);
        model.addAttribute("postById", postById);
        return "post-view";
    }

    @GetMapping("/posts/edit/{id}")
    public String getPostByIdForEdit(@PathVariable(value = "id") Long id, Model model) {
        Post postById = postService.getPostById(id);
        model.addAttribute("postById", postById);
        return "edit-post";
    }

    @PostMapping("/posts/{id}")
    public String saveEditedPost(@ModelAttribute("postById") @Valid Post post,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") Long id
                                 ) {
        if (bindingResult.hasErrors()) {
            return "edit-post";
        }
        Post oldPost = postService.getPostById(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setSummary(post.getSummary());
        oldPost.setContent(post.getContent());
        postService.saveNewPost(oldPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}