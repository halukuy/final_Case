package com.example.halukuyumsal.final_case.entity;

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
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    @Id
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String name;


    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
}