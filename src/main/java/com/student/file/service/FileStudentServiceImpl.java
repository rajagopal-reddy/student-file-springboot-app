package com.student.file.service;

import com.student.file.model.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStudentServiceImpl implements FileStudentService {
    private final String filePath = "students.txt";

    @Override
    public List<Student> getAllStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    Student student = new Student(Long.parseLong(details[0]), details[1], details[2], details[3], Integer.parseInt(details[4]));
                    students.add(student);
                }
            }
        }
        return students;
    }

    @Override
    public void createStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String studentData = String.format("%d,%s,%s,%s,%d%n",
                    student.getId(), student.getName(), student.getEmail(), student.getCourse(), student.getAge());
            writer.write(studentData);
        }
    }

    @Override
    public Student getStudentById(Long id) throws IOException {
        List<Student> students = getAllStudents();
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateStudent(Long id, Student studentDetails) throws IOException {
        List<Student> students = getAllStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    writer.write(String.format("%d,%s,%s,%s,%d%n",
                            id, studentDetails.getName(), studentDetails.getEmail(), studentDetails.getCourse(), studentDetails.getAge()));
                } else {
                    writer.write(String.format("%d,%s,%s,%s,%d%n",
                            student.getId(), student.getName(), student.getEmail(), student.getCourse(), student.getAge()));
                }
            }
        }
    }

    @Override
    public void deleteStudent(Long id) throws IOException {
        List<Student> students = getAllStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                if (!student.getId().equals(id)) {
                    writer.write(String.format("%d,%s,%s,%s,%d%n",
                            student.getId(), student.getName(), student.getEmail(), student.getCourse(), student.getAge()));
                }
            }
        }
    }
}

