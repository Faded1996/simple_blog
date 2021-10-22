package com.bogdanmazur.simple_blog.repositories;

import com.bogdanmazur.simple_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
