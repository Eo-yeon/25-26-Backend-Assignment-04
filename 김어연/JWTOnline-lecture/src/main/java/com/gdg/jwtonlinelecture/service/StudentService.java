package com.gdg.jwtonlinelecture.service;

import com.gdg.jwtonlinelecture.domain.Instructor;
import com.gdg.jwtonlinelecture.domain.Student;
import com.gdg.jwtonlinelecture.dto.StudentDto;
import com.gdg.jwtonlinelecture.repository.InstructorRepository;
import com.gdg.jwtonlinelecture.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public Student addStudent(Long instructorId, StudentDto dto) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 강사입니다."));

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다: " + dto.getEmail());
        }

        Student student = Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .instructor(instructor)
                .build();

        return studentRepository.save(student);
    }

    public List<Student> getStudentsByInstructor(Long instructorId) {
        return studentRepository.findByInstructorId(instructorId);
    }
}