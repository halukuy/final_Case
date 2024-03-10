package com.example.halukuyumsal.final_case.entity;

import com.example.halukuyumsal.final_case.enums.EnumComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REVIEW")
public class Review {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Review")
    @SequenceGenerator(name = "Review", sequenceName = "REVIEW_ID_SEQ")
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "COMMENT", length = 30, nullable = false)
    private EnumComment enumComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
