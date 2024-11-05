package com.student.file.controller;

import com.student.file.model.Student;
import com.student.file.service.FileStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/file-students")
public class FileStudentController {

    @Autowired
    private FileStudentService fileStudentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() throws IOException {
        return fileStudentService.getAllStudents();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws IOException {
        Student student = fileStudentService.getStudentById(id);
        return (student != null) ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createStudent(@RequestBody Student student) throws IOException {
        fileStudentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody Student student) throws IOException {
        fileStudentService.updateStudent(id, student);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) throws IOException {
        fileStudentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}

