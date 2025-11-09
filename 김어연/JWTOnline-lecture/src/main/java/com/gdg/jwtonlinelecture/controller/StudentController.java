package com.gdg.jwtonlinelecture.controller;

import com.gdg.jwtonlinelecture.domain.Student;
import com.gdg.jwtonlinelecture.dto.StudentDto;
import com.gdg.jwtonlinelecture.dto.StudentResponseDto;
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
    public ResponseEntity<List<StudentResponseDto>> getStudentsByInstructor(@PathVariable Long instructorId) {
        List<StudentResponseDto> dtos = studentService.getStudentsByInstructor(instructorId)
                .stream()
                .map(student -> new StudentResponseDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}