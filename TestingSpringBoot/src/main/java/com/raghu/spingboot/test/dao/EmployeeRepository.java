package com.raghu.spingboot.test.dao;

import com.raghu.spingboot.test.ds.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
