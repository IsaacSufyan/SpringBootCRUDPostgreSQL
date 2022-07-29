package com.isaacsufyan.crud.postgresql.crudpostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CrudPostgreSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudPostgreSqlApplication.class, args);
    }

}
