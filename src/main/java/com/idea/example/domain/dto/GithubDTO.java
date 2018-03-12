package com.idea.example.domain.dto;

import lombok.Data;

@Data
public class GithubDTO {

    @Data
    public class ApiData {
        private String total_count;
        private String incomplete_results;
    }

}
