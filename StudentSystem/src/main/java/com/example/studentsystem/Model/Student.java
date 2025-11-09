package com.example.studentsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String Id;
    private String name;
    private String degree;
    private Double gpa;

}
