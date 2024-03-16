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
@Table(name = "USERS")
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    @Id
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String name;


    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "LATITUDE", nullable = false)
    private Long latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Long longitude;
}
