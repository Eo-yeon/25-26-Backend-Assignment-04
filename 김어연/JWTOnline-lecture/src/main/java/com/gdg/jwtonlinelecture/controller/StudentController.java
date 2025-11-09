package com.gdg.jwtonlinelecture.controller;

import com.gdg.jwtonlinelecture.domain.Student;
import com.gdg.jwtonlinelecture.dto.StudentDto;
import com.gdg.jwtonlinelecture.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/{instructorId}")
    public ResponseEntity<Student> addStudent(
            @PathVariable Long instructorId,
            @RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addStudent(instructorId, studentDto));
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<List<Student>> getStudentsByInstructor(@PathVariable Long instructorId) {
        return ResponseEntity.ok(studentService.getStudentsByInstructor(instructorId));
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentDto));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}