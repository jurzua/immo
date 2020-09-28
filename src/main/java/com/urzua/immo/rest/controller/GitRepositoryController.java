package com.urzua.immo.rest.controller;

import com.urzua.immo.model.PersistentRepository;
import com.urzua.immo.rest.form.SearchRepositoryForm;
import com.urzua.immo.service.GithubService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller for the home page.
 */
@Controller
public class GitRepositoryController {

    private final GithubService githubService;

    public GitRepositoryController(final GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("searchRepos", this.githubService.findPersistentRepositories());
            model.addAttribute("persistentRepos", this.githubService.findPersistentRepositories());
            model.addAttribute("searchRepositoryForm", new SearchRepositoryForm());

        }
        return "index";
    }

    @PostMapping("/search")
    public String greetingSubmit(@ModelAttribute SearchRepositoryForm form, Model model) {
        final List<PersistentRepository> list = this.githubService.findPersistentRepositories(form);
        this.githubService.saveAll(list);
        model.addAttribute("searchRepos", list);
        model.addAttribute("persistentRepos", this.githubService.findPersistentRepositories());
        return "index";
    }
}
