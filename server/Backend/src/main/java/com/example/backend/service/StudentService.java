package com.example.backend.service;

import com.example.backend.Exception.InvalidInputException;
import com.example.backend.Exception.StudentNotFoundException;
import com.example.backend.Repository.StudentRepo;
import com.example.backend.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

//       @Autowired
        private final StudentRepo repo;
        
    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }


        public List<Student> getAllStudents() {
            return repo.findAll();
        }

        public Student getStudentById(int id) {
            return repo.findById(id).orElseThrow(() ->
                    new StudentNotFoundException("Student with ID " + id + " not found"));
        }

        public Student addStudent(Student student) {

            if (student.getName().trim().isEmpty()) {
                throw new InvalidInputException("Student name cannot be empty");
            }
            return repo.save(student);
        }

        public Student updateStudent(int id, Student student) throws IOException {
            Student existingStudent = repo.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Student not found"));

            existingStudent.setName(student.getName());

            existingStudent.setEmail(student.getEmail());

            return repo.save(existingStudent);

        }

        public void deleteStudent(int id) {
            if (!repo.existsById(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " not found");
            }
            repo.deleteById(id);
        }

        public List<Student> searchStudents(String keyword) {
            return repo.searchStudents(keyword);
        }
    }


