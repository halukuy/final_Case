package com.example.halukuyumsal.final_case.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

@Getter
@Setter
@SolrDocument(solrCoreName = "final_Case")
public class Restaurant {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "distance", type = "pdouble")
    private double distance;

    @Indexed(name = "distance", type = "pdouble")
    private double latitude;

    @Indexed(name = "distance", type = "pdouble")
    private double longitude;

    @Indexed(name = "rating", type = "pdouble")
    private double rating;
}
