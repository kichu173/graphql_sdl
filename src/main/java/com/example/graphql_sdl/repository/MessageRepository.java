package com.example.graphql_sdl.repository;

import com.example.graphql_sdl.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findAllByAuthor_Id(UUID authorId, Pageable pageable);
}
