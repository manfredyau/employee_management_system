package com.yau.springboot03web.dao;

import com.yau.springboot03web.pojo.Department;
import com.yau.springboot03web.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees;

    private final DepartmentDao departmentDao;

    public static int currentId = 1001;

    static {
        employees = new HashMap<>();
        employees.put(currentId++, new Employee(1001, "A", "Manfred3431@outlook.com",
                1, new Department(1001, "教學部"), new Date()));
        employees.put(currentId++, new Employee(1002, "B", "Manfred3431@outlook.com",
                1, new Department(1002, "市場部"), new Date()));
        employees.put(currentId++, new Employee(1003, "C", "Manfred3431@outlook.com",
                1, new Department(1003, "教研部"), new Date()));
        employees.put(currentId++, new Employee(1004, "D", "Manfred3431@outlook.com",
                1, new Department(1004, "運營部"), new Date()));
        employees.put(currentId++, new Employee(1005, "E", "Manfred3431@outlook.com",
                1, new Department(1005, "後勤部"), new Date()));
    }

    public EmployeeDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void addEmployee(Employee employee) {
        if (employee.getId() == 0) {
            employee.setId(currentId);
        }
        System.err.println(employee.getDepartment().getId());
        employee.getDepartment()
                .setDepartmentName(departmentDao.getDepartmentById(employee.getDepartment().getId()).getDepartmentName());
        employees.put(employee.getId(), employee);
        currentId++;
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    public void removeEmployee(int id) {
        employees.remove(id);
    }
}
