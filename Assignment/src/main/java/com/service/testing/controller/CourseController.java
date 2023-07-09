package com.service.testing.controller;

import com.service.testing.model.Course;
import com.service.testing.model.Schedule;
import com.service.testing.model.Student;
import com.service.testing.repository.CourseRepository;
import com.service.testing.repository.ScheduleRepository;
import com.service.testing.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @GetMapping("/all")
    public List<Course> getClassroomList() {
        return courseService.getCourseList();
    }

    @GetMapping("/{courseId}")
    public Object getCourseById(@PathVariable long courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/add")
    public Object addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/update/{courseId}")
    public Object updateCourse(@PathVariable long courseId, @RequestBody Course course){
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("/delete/{courseId}")
    public Object deleteCourseById(@PathVariable long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping("/{courseId}/schedule/{scheduleId}")
    public Object addCourseToStudent(@PathVariable Long courseId, @PathVariable Long scheduleId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        logger.info("course: " + course);
        if (course.isPresent() && schedule.isPresent()) {
            Course updatedCourse = course.get();
            logger.info("updatecourse: " + updatedCourse);
            updatedCourse.getSchedule().add(schedule.get());
            return courseRepository.save(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{courseId}/schedule/{scheduleId}")
//    public Object deleteScheduleFromCourse(@PathVariable Long courseId, @PathVariable Long scheduleId){
//        Optional<Course> course = courseRepository.findById(courseId);
//        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
//        if(course.isPresent()&&schedule.isPresent()){
//            Course course1 = course.get();
//            logger.info("course1: " + course1);
//            course1.getSchedule().remove(scheduleId);
//            logger.info("course1: " + course1.getSchedule().remove(schedule.get(scheduleId)));
//            return courseRepository.findById(scheduleId);
//        }
//        return "SORRY";
//    }
}
