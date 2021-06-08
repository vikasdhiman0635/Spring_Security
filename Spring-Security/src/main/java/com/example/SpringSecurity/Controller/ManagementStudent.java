package com.example.SpringSecurity.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class ManagementStudent
{
    private static final List<student> STUDENTS= Arrays.asList(
            new student(1,"Vikas"),
            new student(2,"Tanuj"),
            new student(3,"Jyoti")
    );


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<student> getaStudents()
    {
        System.out.println("getStudents");
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void insternewStudent(@RequestBody student student)
    {
        System.out.println("insternewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "/{studentid}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delete(@PathVariable("studentid") Integer studentid)
    {
        System.out.println("delete");
        System.out.println(studentid);
    }

    @PutMapping(path = "/{studentid}")
    @PreAuthorize("hasAuthority('student:write')")
    public void update(@PathVariable("studentid") Integer studentid,@RequestBody student student)
    {
        System.out.println("update");
        System.out.println(String.format("%s %s",student, studentid));
    }

}
