package com.urzua.immo.mapper;

import com.urzua.immo.model.PersistentRepository;
import com.urzua.immo.model.User;
import com.urzua.immo.service.model.JsonRepository;
import org.springframework.stereotype.Component;

@Component
public class RepoMapper {

    public PersistentRepository toDao(final JsonRepository json) {
        return PersistentRepository.builder()
                .blobsUrl(json.getBlobsUrl())
                .name(json.getName())
                .fullName(json.getFullName())
                .id(json.getId())
                .commits(json.getCommits())
                .pulls(json.getPulls())
                .user(User.builder()
                        .id(json.getOwner().getId())
                        .login(json.getOwner().getLogin())
                        .build())
                .build();
    }

}
