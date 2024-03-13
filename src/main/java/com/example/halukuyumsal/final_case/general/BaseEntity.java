package com.example.halukuyumsal.final_case.general;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel {

  @Embedded
  private BaseAdditionalFields baseAdditionalFields;
}
