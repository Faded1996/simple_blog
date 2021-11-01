package com.bogdanmazur.simple_blog.services;

import com.bogdanmazur.simple_blog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void saveNewPost(Post post);

    Post getPostById(Long id);

    void deletePost(Long id);
}
