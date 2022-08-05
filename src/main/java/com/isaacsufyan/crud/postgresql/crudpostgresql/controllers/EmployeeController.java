package com.isaacsufyan.crud.postgresql.crudpostgresql.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.isaacsufyan.crud.postgresql.crudpostgresql.exception.ResourceNotFoundException;
import com.isaacsufyan.crud.postgresql.crudpostgresql.model.Employee;
import com.isaacsufyan.crud.postgresql.crudpostgresql.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/employees")
    public MappingJacksonValue getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","firstName", "lastName","emailId");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeList);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<MappingJacksonValue> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","firstName", "lastName","emailId");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.status(HttpStatus.OK).body(mappingJacksonValue);
    }

    @PostMapping("/employees")
    public MappingJacksonValue createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","firstName", "lastName","emailId");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(savedEmployee);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<MappingJacksonValue> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName","emailId");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(updatedEmployee);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Object> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Object> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        response.put("status", HttpStatus.OK);
        response.put("status_code", HttpStatus.OK.value());
        return response;
    }
}