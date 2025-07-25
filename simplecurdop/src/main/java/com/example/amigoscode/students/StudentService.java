package com.example.amigoscode.students;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }



    public List<Student> getStudents(){

       return studentRepository.findAll();
    }



    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already exist");
        }else{
            studentRepository.save(student);
        }
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean isdexist = studentRepository.existsById(studentId);
        if(!isdexist){
            throw new IllegalStateException("Student with id "+studentId+" not exist");
        }else{
            studentRepository.deleteById(studentId);
        }


    }

    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id "+studentId+"does not exist"));

        if(name !=null && !name.isEmpty() && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email!=null && !email.isEmpty() && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException(email +" this email is already taken by other user!");
            }
            student.setEmail(email);
        }
    }
}
