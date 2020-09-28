package com.urzua.immo.service;

import com.urzua.immo.config.properties.GithubServiceProperties;
import com.urzua.immo.mapper.RepoMapper;
import com.urzua.immo.model.PersistentRepository;
import com.urzua.immo.repository.AnalysedRepositoryRepository;
import com.urzua.immo.rest.form.SearchRepositoryForm;
import com.urzua.immo.service.model.JsonRepository;
import com.urzua.immo.service.model.PullRequest;
import com.urzua.immo.service.model.RepositoryCommit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private final RestTemplate restTemplate;
    private final GithubServiceProperties properties;
    private final AnalysedRepositoryRepository repositoryRepository;
    private final RepoMapper repoMapper;

    public GithubService(final RestTemplate restTemplate,
                         final GithubServiceProperties properties,
                         final AnalysedRepositoryRepository repositoryRepository,
                         final RepoMapper repoMapper) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        this.repositoryRepository = repositoryRepository;
        this.repoMapper = repoMapper;
    }

    public List<PersistentRepository> findPersistentRepositories() {
        return repositoryRepository.findAll();
    }

    @Transactional
    public void saveAll(final List<PersistentRepository> list) {
        repositoryRepository.saveAll(list);
    }

    public List<PersistentRepository> findPersistentRepositories(final SearchRepositoryForm form) {
        return Arrays.stream(findGitRepositories())
                .filter(jsonRepository -> form.getRepositoryName().isBlank() || jsonRepository.getName().contains(form.getRepositoryName()))
                .filter(jsonRepository -> form.getOwner().isBlank() || jsonRepository.getOwner().getLogin().contains(form.getOwner()))
                .map(jsonRepository -> repoMapper.toDao(jsonRepository))
                .collect(Collectors.toList());
    }

    public JsonRepository[] findGitRepositories() {
        final JsonRepository[] result = restTemplate.getForObject(
                "https://api.github.com/users/jurzua/repos",
                JsonRepository[].class);


        Arrays.stream(result).forEach(repository -> {
            repository.setBlobsUrl(repository.getBlobsUrl().replace("{/sha}", ""));
            repository.setCommits(getCommits(repository).length);
            repository.setPulls(getPulls(repository).length);
        });

        System.out.println();
        return result;
    }

    public RepositoryCommit[] getCommits(final JsonRepository repository) {
        /*
        final RepositoryCommit[] result = restTemplate.getForObject(
                repository.getCommitsUrl().replace("{/sha}", ""),
                RepositoryCommit[].class);
        return result;
        */
        return new RepositoryCommit[] {RepositoryCommit.builder().sha("aaa").url("www").build()};
    }

    public PullRequest[] getPulls(final JsonRepository repository) {
        /*
        final PullRequest[] result = restTemplate.getForObject(
                repository.getPullsUrl().replace("{/number}", ""),
                PullRequest[].class);
        return result;*/
        return new PullRequest[] {PullRequest.builder().id(1L).url("www").build()};
    }
}
