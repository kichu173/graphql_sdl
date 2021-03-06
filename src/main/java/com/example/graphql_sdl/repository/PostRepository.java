package com.example.graphql_sdl.repository;

import com.example.graphql_sdl.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByAuthor_Id(UUID authorId);
}
