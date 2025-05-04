package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "occupation")
    private String occupation;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department departments;


}
