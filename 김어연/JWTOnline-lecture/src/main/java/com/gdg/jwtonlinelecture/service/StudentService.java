package com.gdg.jwtonlinelecture.service;

import com.gdg.jwtonlinelecture.domain.Instructor;
import com.gdg.jwtonlinelecture.domain.Student;
import com.gdg.jwtonlinelecture.dto.StudentDto;
import com.gdg.jwtonlinelecture.repository.InstructorRepository;
import com.gdg.jwtonlinelecture.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    @Transactional
    public Student addStudent(Long instructorId, StudentDto studentDto) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
        Student student = Student.builder().name(studentDto.getName()).email(studentDto.getEmail()).instructor(instructor).build();
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByInstructor(Long instructorId) {
        return studentRepository.findByInstructorId(instructorId);
    }

    @Transactional
    public Student updateStudent(Long studentId, StudentDto studentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Instructor instructor = instructorRepository.findById(studentDto.getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
        return studentRepository.save(student.update(studentDto.getName(), studentDto.getEmail(), instructor));
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student not found");
        }
        studentRepository.deleteById(studentId);
    }
}