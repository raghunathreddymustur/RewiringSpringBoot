package com.raghu.spingboot.test.controller;

import com.raghu.spingboot.test.dao.EmployeeRepository;
import com.raghu.spingboot.test.ds.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    private static final Employee EMPLOYEE_1 = new Employee(1, "John", "Doe", "John.Doe@corp.com");
    private static final Employee EMPLOYEE_2 = new Employee(2, "Dave", "Doyle", "Dave.Doyle@corp.com");
    private static final Employee EMPLOYEE_3 = new Employee(3, "Tom", "Frost", "Tom.Frost@corp.com");

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldStoreEmployees() {
        employeeRepository.saveAll(asList(
                EMPLOYEE_1,
                EMPLOYEE_2
        ));

        assertEquals(EMPLOYEE_1, testEntityManager.find(Employee.class, 1));
        assertEquals(EMPLOYEE_2, testEntityManager.find(Employee.class, 2));
    }

    @Test
    public void shouldFindEmployeesByEmails() {
        testEntityManager.merge(EMPLOYEE_1);
        testEntityManager.merge(EMPLOYEE_2);
        testEntityManager.merge(EMPLOYEE_3);

        assertEquals(EMPLOYEE_1, employeeRepository.findByEmail(EMPLOYEE_1.getEmail()));
        assertEquals(EMPLOYEE_2, employeeRepository.findByEmail(EMPLOYEE_2.getEmail()));
        assertEquals(EMPLOYEE_3, employeeRepository.findByEmail(EMPLOYEE_3.getEmail()));
    }
}
