package com.example.graphql_sdl.services.Impl;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.model.Author;
import com.example.graphql_sdl.model.Post;
import com.example.graphql_sdl.repository.AuthorRepository;
import com.example.graphql_sdl.repository.PostRepository;
import com.example.graphql_sdl.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.postRepository = repository;
    }

    @Override
    public List<PostDto> getAllPostByAuthorId(UUID authorId) {
        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(authorId);
        return allByAuthor_id.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(authorId)
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getRecentPosts(int count, int offset) {// page 1(offset), size 2(count)
        PageRequest of = PageRequest.of(offset, count); // offset ->How many page we want to skip,count -> size of the page
        Page<Post> all = postRepository.findAll(of);
        return all.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(post.getAuthor().getId())
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDto postDto) {
        Author author = authorRepository.findById(postDto.getAuthorId()).get();
        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .author(author)
                .build();
        Post post = postRepository.saveAndFlush(newPost);
        return post.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return postRepository.findAllByAuthor_Id(id).size();
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new RuntimeException("Post does not exist!");
        }
        Post post = postOptional.get();
        return PostDto.builder()
                .id(post.getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .description(post.getDescription())
                .authorId(post.getAuthor().getId())
                .build();
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (!authorOptional.isPresent()) {
            throw new RuntimeException("Author does not exist!");
        }
        Author author = authorOptional.get();
        return AuthorDto.builder()
                .id(authorId)
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }
}
