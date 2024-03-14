package com.example.halukuyumsal.final_case.dto;

import com.example.halukuyumsal.final_case.enums.EnumComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private EnumComment enumComment;
    private Long userId;

}