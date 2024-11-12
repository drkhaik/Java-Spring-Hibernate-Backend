package com.springjpahibernate.cruddemo.rest;


import com.springjpahibernate.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("John", "Doe", "johndoe@gmail.com"));
        theStudents.add(new Student("Jane", "Marry", "johndoe@gmail.com"));
        theStudents.add(new Student("Zayn", "Ink", "johndoe@gmail.com"));

    }

    // define endpoint for "/students" - return list of student
    @GetMapping("/students")
    public List<Student> getStudents(){
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

}
