package com.example.halukuyumsal.final_case.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "com.example.repository")
public class SolrConfig {

    private final String SOLR_URL = "http://localhost:8983/solr";

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(SOLR_URL).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }
}