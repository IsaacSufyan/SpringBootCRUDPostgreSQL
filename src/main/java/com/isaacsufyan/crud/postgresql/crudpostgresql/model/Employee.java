package com.isaacsufyan.crud.postgresql.crudpostgresql.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "employees")
@JsonFilter("filter")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Size(min = 1)
    private String firstName;

    @NonNull
    @Size(min = 1)
    private String lastName;

    @NonNull
    @Email
    private String emailId;

}