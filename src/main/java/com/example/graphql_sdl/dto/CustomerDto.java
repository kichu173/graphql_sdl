package com.example.graphql_sdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private LocalDate birthday;
    private OffsetTime workStartTime;
    private OffsetDateTime bornAt;
    private String profileLink;
}
