package com.bogdanmazur.simple_blog.services;

import com.bogdanmazur.simple_blog.entity.Post;
import com.bogdanmazur.simple_blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public   List<Post> getAllPosts() {
      return postRepository.findAll();
    }

    public void saveNewPost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
