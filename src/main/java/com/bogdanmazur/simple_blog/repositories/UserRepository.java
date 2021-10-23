package com.bogdanmazur.simple_blog.repositories;

import com.bogdanmazur.simple_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//  Name this method by following convention naming
    User findByEmail(String email);
}
