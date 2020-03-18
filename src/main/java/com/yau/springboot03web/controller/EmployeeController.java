package com.yau.springboot03web.controller;

import com.yau.springboot03web.dao.DepartmentDao;
import com.yau.springboot03web.dao.EmployeeDao;
import com.yau.springboot03web.pojo.Department;
import com.yau.springboot03web.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public EmployeeController(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @GetMapping("/showEmployees")
    public String list(Model model) {
        Collection<Employee> allEmployees = employeeDao.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);
        return "employee/list";
    }

    @GetMapping("/addEmployeePage")
    public String addEmployeePage(Model model) {
        Collection<Department> departments = departmentDao.getAllDepartments();
        model.addAttribute("departments", departments);
        return "employee/addEmployeePage";
    }

    /*注意，這裡和上面的函數所映射的地址是一樣的，只是這裡只接受 post 型的請求*/
    @PostMapping("/addEmployeePage")
    public String addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        return "redirect:/showEmployees";
    }

    //員工編輯頁面
    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable int id,Model model) {
        //TODO
        System.err.println(id);
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("editEmployee", employee);

        // 獲得所有部門，以便於前端頁面的渲染
        Collection<Department> departments = departmentDao.getAllDepartments();
        model.addAttribute("departments", departments);
        return "employee/editEmployeePage";
    }

    //提交員工編輯
    @PostMapping("/commitEditEmployee")
    public String commitEditEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        return "redirect:/showEmployees";
    }

    //刪除一名員工
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeDao.removeEmployee(id);
        return "redirect:/showEmployees";
    }
}
