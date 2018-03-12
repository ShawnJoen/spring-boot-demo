package com.idea.example.controller;

import com.idea.example.domain.dto.GithubDTO;
import com.idea.example.provider.feign.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feign/")
public class FeignController {

    private final GithubProvider githubProvider;

    @Autowired
    public FeignController(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }
//http://127.0.0.1:8080/feign/github
    @GetMapping("github")
    public @ResponseBody
    GithubDTO.ApiData ticker() {

        log.info("-------------github");

        return githubProvider.github();
    }
//http://127.0.0.1:8080/feign/github/jack
    @RequestMapping(value = "github/{q}", method = RequestMethod.GET)
    public @ResponseBody
    GithubDTO.ApiData ticker2(@PathVariable("q") String q) {

        log.info("-------------github:" + q);

        return githubProvider.githubq(q);
    }

}
