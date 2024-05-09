package com.company.product.services.school.controller;

import com.company.product.services.school.service.StudentService;
import com.company.product.services.school.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class is where all the user requests are handled and required/appropriate
 * responses are sent
 */
@RestController
@RequestMapping("/api/students/v1")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/api/students/v1/
     * Purpose: Fetches all the students in the student table
     *
     * @return List of Students
     */
    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/api/students/v1/1 (or any other id)
     * Purpose: Fetches student with the given id
     *
     * @param id - student id
     * @return Student with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student resp = studentService.getStudentById(id);
        if (resp != null) {
            return ResponseEntity.ok().body(resp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/api/students/v1/ram (or any other name)
     * Purpose: Fetches student with the given name
     *
     * @param name - student name
     * @return Student with the given name
     */
    @GetMapping("/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
        List<Student> resp = studentService.getStudentByName(name);
        if (resp != null && !resp.isEmpty()) {
            return ResponseEntity.ok().body(resp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/api/students/v1/class/4 (or any other class id)
     * Purpose: Fetches student with the given class id
     *
     * @param id - class id
     * @return Student with the given class id
     */
    @GetMapping("/class/{id}")
    public ResponseEntity<List<Student>> getStudentByClass(@PathVariable int id) {
        List<Student> resp = studentService.getStudentByClass(id);
        if (resp != null && !resp.isEmpty()) {
            return ResponseEntity.ok().body(resp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is called when a POST request is made
     * URL: localhost:8080/api/students/v1/
     * Purpose: Save a Student entity
     *
     * @param student - Request body is a Student entity
     * @return Saved Student entity
     */
    @PostMapping("/")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.saveStudent(student));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/api/students/v1/
     * Purpose: Update a Student entity
     *
     * @param student - Student entity to be updated
     * @return Updated Student
     */
    @PutMapping("/")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateResp = studentService.updateStudent(student);
        if (updateResp != null) {
            return ResponseEntity.ok().body(updateResp);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/api/students/v1/1 (or any other id)
     * Purpose: Delete a Student entity
     *
     * @param id - student's id to be deleted
     * @return a String message indicating student record has been deleted successfully
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().body("Deleted student successfully");
    }
}
