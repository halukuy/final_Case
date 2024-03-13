package com.example.halukuyumsal.final_case.general;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

  private Long latitude;
  private Long longlatitude;
}
