package com.example.backend.Repository;

import com.example.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

        @Query("SELECT s FROM Student s WHERE " +
                "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<Student> searchStudents(@Param("keyword") String keyword);

    }

