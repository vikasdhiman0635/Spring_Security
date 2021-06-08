package com.example.SpringSecurity.Controller;

public class student
{
    private final Integer studentId;

    private  final String studentname;

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentname() {
        return studentname;
    }

    public student(Integer studentId, String studentname) {
        this.studentId = studentId;
        this.studentname = studentname;
    }

    @Override
    public String toString() {
        return "student{" +
                "studentId=" + studentId +
                ", studentname='" + studentname + '\'' +
                '}';
    }
}
