package com.isaacsufyan.crud.postgresql.crudpostgresql.repositories;

import com.isaacsufyan.crud.postgresql.crudpostgresql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}