package com.bogdanmazur.simple_blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Must not be empty")
    private String title;

    @NotBlank(message = "Must not be empty")
    private String summary;

    @Lob
    @NotBlank(message = "Must not be empty")
    private String content;


}
