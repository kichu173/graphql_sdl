package com.example.graphql_sdl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name ="post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;
    private String title;
    private String description;
    private String category;
    @ManyToOne
    @JoinColumn(name ="author_id")
    private Author author;
    @OneToMany(mappedBy = "post")
    private Set<Message> messages;
}
