package com.example.backend.Controller;

import com.example.backend.DTO.CommonApiResponse;
import com.example.backend.model.Student;
import com.example.backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
//    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<CommonApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(new CommonApiResponse<>(200, "Students fetched successfully", students));
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<CommonApiResponse<Student>> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);
        return ResponseEntity.ok(new CommonApiResponse<>(200, "Student fetched successfully", student));
    }


    @PostMapping("/students")
    public ResponseEntity<CommonApiResponse<Student>> addStudent(@Valid @RequestBody Student student) {
        Student savedStudent = service.addStudent(student);
        return new ResponseEntity<>(new CommonApiResponse<>(201, "Student added successfully", savedStudent), HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
  public ResponseEntity<CommonApiResponse<Student>> updateStudent(@PathVariable int id, @Valid @RequestBody Student student) throws IOException {
    Student updatedStudent = service.updateStudent(id, student);
    return ResponseEntity.ok(new CommonApiResponse<>(200, "Student updated successfully", updatedStudent));
}

    @DeleteMapping("/students/{id}")
    public ResponseEntity<CommonApiResponse<String>> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.ok(new CommonApiResponse<>(200, "Student deleted successfully", "Deleted ID: " + id));
    }
//        if (Student!= null){
//            service.deleteStudent(id);
//            return  new ResponseEntity<>("Deleted",HttpStatus.OK);
//
//        }
//        else
//            return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);



    @GetMapping("/Students/search")
    public ResponseEntity<CommonApiResponse<List<Student>>> searchStudents(@RequestParam String keyword) {
        List<Student> students = service.searchStudents(keyword);
        return ResponseEntity.ok(new CommonApiResponse<>(200, "Students fetched successfully", students));
    }

}
