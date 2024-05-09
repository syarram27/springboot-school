package com.company.product.services.school.service;

import com.company.product.services.school.model.Student;
import com.company.product.services.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer is where all the business logic gets encapsulated
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        log.info("Student with id: {} doesn't exist", id);
        return null;
    }

    public List<Student> getStudentByName(String name) {
        List<Student> studentRepoByName = studentRepo.findByName(name);
        if (!CollectionUtils.isEmpty(studentRepoByName)) {
            return studentRepoByName;
        }
        log.info("Student with name: {} doesn't exist", name);
        return null;
    }

    public List<Student> getStudentByClass(int studentClass) {
        List<Student> repoByStudentClass = studentRepo.findByStudentClass(studentClass);
        if (!CollectionUtils.isEmpty(repoByStudentClass)) {
            return repoByStudentClass;
        }
        log.info("Students with class: {} doesn't exist in the school", studentClass);
        return null;
    }

    public Student saveStudent(Student student) {
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student savedStudent = studentRepo.save(student);

        log.info("Student with id: {} saved successfully", student.getId());
        return savedStudent;
    }

    public Student updateStudent(Student student) {
        Optional<Student> existingStudent = studentRepo.findById(student.getId());
        Student updatedStudent = null;
        if (existingStudent.isPresent()) {
            student.setCreatedAt(existingStudent.get().getCreatedAt());
            student.setUpdatedAt(LocalDateTime.now());

            updatedStudent = studentRepo.save(student);

            log.info("Student with id: {} updated successfully", student.getId());
        } else {
            log.error("Student with id: {} doesn't exist in the school", student.getId());
        }
        return updatedStudent;
    }

    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }
}
