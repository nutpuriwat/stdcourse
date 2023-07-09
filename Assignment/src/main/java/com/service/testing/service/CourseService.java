package com.service.testing.service;

import com.service.testing.model.Course;
import com.service.testing.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired private CourseRepository courseRepository;

    Logger logger = LoggerFactory.getLogger(CourseService.class);
    public List<Course> getCourseList(){
        return courseRepository.findAll();
    }

    public Object getCourseById(long courseId) {
        return courseRepository.findById(courseId);
    }

    public Object addCourse(Course course) {
        logger.info("addCourse Service Successful.");
        return courseRepository.save(course);
    }

    public Object updateCourse(long courseId, Course course) {
        Course courseDB = new Course();
        if(courseRepository.findById(courseId).isPresent()){
            courseDB = courseRepository.findById(courseId).get();
            courseDB.setCourse_id(course.getCourse_id());
            logger.info("courseId:"+course.getCourse_id());
            courseDB.setCourse_name(course.getCourse_name());
            return courseRepository.save(courseDB);
        }
        return ("SORRY");
    }

    public Object deleteCourseById(long courseId) {
        Course course = new Course();
        if(courseRepository.findById(courseId).isPresent()){
            course = courseRepository.findById(courseId).get();
            courseRepository.deleteById(courseId);
        }
        return course;
    }



//    public void addStudentToClassroom(long classroomId, long studentId) {
//        Course course = courseRepository.findById(classroomId).get();
//        classroom.addStudentToClassroom(studentId);
//        courseRepository.save(classroom);
//    }
//
//    public void deleteStudentFromClassroomById(long classroomId, long studentId) {
//        Course course = courseRepository.findById(classroomId).get();
//        classroom.removeStudentFromClassroom(studentId);
//        courseRepository.save(classroom);
//    }
}
