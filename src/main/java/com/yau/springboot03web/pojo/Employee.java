package com.yau.springboot03web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String lastName;
    private String email;
    // 0 for female, 1 for male
    private int gender;
    private Department department;
    private Date birth;
}
