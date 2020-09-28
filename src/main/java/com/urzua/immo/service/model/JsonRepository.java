package com.urzua.immo.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonRepository {
    private Long id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String url;
    @JsonProperty("commits_url")
    private String commitsUrl;
    @JsonProperty("pulls_url")
    private String pullsUrl;
    private RepositoryOwner owner;
    @JsonProperty("blobs_url")
    private String blobsUrl;

    private Integer commits;
    private Integer pulls;

}
