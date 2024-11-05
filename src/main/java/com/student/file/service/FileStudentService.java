package com.student.file.service;

import com.student.file.model.Student;

import java.io.IOException;
import java.util.List;

public interface FileStudentService {
    public List<Student> getAllStudents() throws IOException;
    public void createStudent(Student student) throws IOException;
    public Student getStudentById(Long id) throws IOException;
    public void updateStudent(Long id, Student studentDetails) throws IOException;
    public void deleteStudent(Long id) throws IOException;
}
