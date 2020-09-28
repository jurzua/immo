package com.urzua.immo.rest.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRepositoryForm {
    private String repositoryName;
    private String owner;
}
