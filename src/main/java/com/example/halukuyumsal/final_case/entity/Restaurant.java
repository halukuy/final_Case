package com.example.halukuyumsal.final_case.entity;

import com.example.halukuyumsal.final_case.general.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

@Getter
@Setter
@SolrDocument(collection = "restaurant")
public class Restaurant extends BaseEntity {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "distance", type = "pdouble")
    private double distance;
}
