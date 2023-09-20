package com.reactive.config;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class GitRespository {
    private String description;
    private Integer forks;
    @JsonAlias("stargazers_count")
    private Integer stars;
}
