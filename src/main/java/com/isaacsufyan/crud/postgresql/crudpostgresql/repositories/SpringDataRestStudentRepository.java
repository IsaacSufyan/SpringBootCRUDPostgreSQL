package com.isaacsufyan.crud.postgresql.crudpostgresql.repositories;

import com.isaacsufyan.crud.postgresql.crudpostgresql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "student", path = "student")
public interface SpringDataRestStudentRepository extends JpaRepository<Student, Long> {

}