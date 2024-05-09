package com.company.product.services.school.repository;

import com.company.product.services.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository is an interface that provides access to data in a database
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByStudentClass(int studentClass);
}
