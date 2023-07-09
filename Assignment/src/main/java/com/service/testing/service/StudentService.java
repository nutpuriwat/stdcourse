package com.service.testing.service;

import com.service.testing.model.Course;
import com.service.testing.model.Student;
import com.service.testing.repository.CourseRepository;
import com.service.testing.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public List<Student> getStudentList(){
        logger.info("getStudentList Service Successful.");
        return studentRepository.findAll();
    }

    public Object getStudentById(long studentId) {
        logger.info("getStudentById Service Successful.");
        return studentRepository.findById(studentId);
    }

    public Object addStudent(Student student) {
        logger.info("addStudent Service Successful.");
        return studentRepository.save(student);
    }

    public Object updateStudent(long studentId, Student student){
        Student studentDB = new Student();
        if (studentRepository.findById(studentId).isPresent()) {
            studentDB = studentRepository.findById(studentId).get();
            if (student.getStudent_name() != null) {
                studentDB.setStudent_name(student.getStudent_name());
            }
            studentRepository.save(studentDB);
            logger.info("updateStudent Service Successful.");
            return studentRepository.save(studentDB);
        }
        return ("Student ID does not exist");
    }

    public Object deleteStudent(long studentId) {
        Student student = new Student();
        if(studentRepository.findById(studentId).isPresent()){
            student = studentRepository.findById(studentId).get();
            studentRepository.delete(student);
            logger.info("deleteStudent Service Successful.");
        }
        return student;
    }

    public List<Course> getCoursesByStudentId(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        List<Course> courses = new ArrayList<>();
        if (student.isPresent()) {
            courses = student.get().getCourses();
        }
        return courses;
    }
    public Object deleteCourseFromStudent(long studentId, long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if(student.isPresent()&&course.isPresent()){
            Student updateStudent = student.get();
            Course courseToDelete = course.get();
            LocalDate startdate = courseToDelete.getCourse_start();
            LocalDate currentDate = LocalDate.now();
            LocalDate deleteDate = startdate.minusDays(7);

            if(currentDate.isBefore(deleteDate)){
                courseToDelete.setSeat(courseToDelete.getSeat()+1);
                updateStudent.getCourses().remove(courseToDelete);
                studentRepository.save(updateStudent);
                return studentRepository.save(updateStudent);
            } else {
                return ("You can cancel the course in advance up to 7 days before it starts.");
            }
        } else {
            return ("SORRY");
        }
    }


}
