package com.service.testing.controller;

import com.service.testing.model.Course;
import com.service.testing.model.Student;
import com.service.testing.model.Schedule;
import com.service.testing.repository.CourseRepository;
import com.service.testing.repository.StudentRepository;
import com.service.testing.service.CourseService;
import com.service.testing.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/all")
    public List<Student> getStudentList() {
        return studentService.getStudentList();
    }

    @GetMapping("/{studentId}")
    public Object getStudentById(@PathVariable long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/add")
    public Object addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("update/{studentId}")
    public Object updateStudent(@PathVariable long studentId, @RequestBody Student student){
        return studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/delete/{studentId}")
    public Object deleteStudent(@PathVariable long studentId){
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            List<Course> courses = student.get().getCourses();
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public Object addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (student.isPresent() && course.isPresent()) {
            Student updatedStudent = student.get();
            Course updatedCourse = course.get();

            if (updatedCourse.isScheduleOverlap(updatedCourse)) {
                return ("Course-Overlap");
            }

            logger.info("Seat: " + updatedCourse.getSeat());
            if (updatedCourse.getSeat() > 0) {
                updatedStudent.getCourses().add(updatedCourse);
                updatedCourse.setSeat(updatedCourse.getSeat() - 1);
                return studentRepository.save(updatedStudent);
            } else {
                return ("Course-Full");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/{studentId}/courses/{courseId}")
//    public Object addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
//        Optional<Student> student = studentRepository.findById(studentId);
//        Optional<Course> course = courseRepository.findById(courseId);
//
//        if (student.isPresent() && course.isPresent()) {
//            Student updatedStudent = student.get();
//            Course updateCourse = course.get();
//            logger.info("Seat: " + updateCourse.getSeat());
//            if(updateCourse.getSeat()>0){
//                updatedStudent.getCourses().add(course.get());
//                updateCourse.setSeat(updateCourse.getSeat()-1);
//                return studentRepository.save(updatedStudent);
//            } else {
//                return ("Course-Full");
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/{studentId}/courses/{courseId}")
//    public Object addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
//        Optional<Student> student = studentRepository.findById(studentId);
//        Optional<Course> course = courseRepository.findById(courseId);
//
//        if (student.isPresent() && course.isPresent()) {
//            Student updatedStudent = student.get();
//            updatedStudent.getCourses().add(course.get());
//            return studentRepository.save(updatedStudent);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



//    private boolean courseTimeAvailable(Course updateCourse){
//        List<Course> courses = courseRepository.findAll();
//        for(Course course : courses){
//            if(course.getSchedule().)
//        }
//    }

//    @PostMapping("/{studentId}/courses/{courseId}")
//    public Object addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
//        Optional<Student> student = studentRepository.findById(studentId);
//        Optional<Course> course = courseRepository.findById(courseId);
//
//        if (student.isPresent() && course.isPresent()) {
//            Student updatedStudent = student.get();
//            Course courseToAdd = course.get();
//
//            boolean isCourseConflict = updatedStudent.getCourses().stream()
//                    .anyMatch(c -> (c.getSchedule().getDate1().equals(courseToAdd.getSchedule().getDate1())
//                            && c.getSchedule().getDate1starttime().equals(courseToAdd.getSchedule().getDate1starttime()))
//                    );
//
//            if(!isCourseConflict) {
//                updatedStudent.getCourses().add(courseToAdd);
//                studentRepository.save(updatedStudent);
//                return studentRepository.save(updatedStudent);
//            }
//            return ("CHECK TIME");
//        } else {
//            return ("NOT FOUND");
//        }
//    }

//    c.getSchedule().getDate1().equals(courseToAdd.getSchedule().getDate1())
//            && c.getSchedule().getDate1starttime().equals(courseToAdd.getSchedule().getDate1starttime()))
//            && (c.getSchedule().getDate2().equals(courseToAdd.getSchedule().getDate2())
//            && c.getSchedule().getDate2starttime().equals(courseToAdd.getSchedule().getDate2starttime()))
//            && (c.getSchedule().getDate3().equals(courseToAdd.getSchedule().getDate3())
//            && c.getSchedule().getDate3starttime().equals(courseToAdd.getSchedule().getDate3starttime())

//    boolean isCourseConflict = updatedStudent.getCourses().stream()
//            .anyMatch(c -> c.getSchedule().getDayOfWeek().equals(courseToAdd.getSchedule().getDayOfWeek())
//                    && c.getSchedule().getStartTime().equals(courseToAdd.getSchedule().getStartTime()));
//
//        if (!isCourseConflict) {
//        updatedStudent.getCourses().add(courseToAdd);
//        studentRepository.save(updatedStudent);
//        return ResponseEntity.ok().build();
//    } else {
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }
//} else {
//        return ResponseEntity.notFound().build();
//        }

    @DeleteMapping("/{studentId}/course/{courseId}")
    public Object deleteCourseFromStudent(@PathVariable long studentId, @PathVariable long courseId){
        return studentService.deleteCourseFromStudent(studentId,courseId);
    }
}
