package com.example.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController
{
    private static final List<student> STUDENTS= Arrays.asList(
            new student(1,"Vikas"),
            new student(2,"Tanuj"),
            new student(3,"Jyoti")
    );

    @GetMapping(path = "/{studentId}")
    public student getStudent(@PathVariable("studentId") Integer studentId)
    {
        return STUDENTS.stream()
                .filter(student->studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Student "+studentId + " does not exist"));
    }

}
