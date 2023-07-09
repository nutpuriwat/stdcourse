package com.service.testing.controller;

import com.service.testing.model.Schedule;
import com.service.testing.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/{courseId}")
    public Object getScheduleFromCourseId(@PathVariable long courseId){
        return scheduleService.getScheduleFromCourseId(courseId);
    }
    @PostMapping("/add-schedule/course/{courseId}")
    public Object addSchedule(@PathVariable long courseId, @RequestBody Schedule schedule){
        return scheduleService.addSchedule(courseId, schedule);
    }

    @PutMapping("update-schedule/{scheduleId}")
    public Object updateSchedule(@PathVariable long scheduleId, @RequestBody Schedule schedule){
        return scheduleService.updateSchedule(scheduleId, schedule);
    }

    @DeleteMapping("delete-schedule/course/{courseId}")
    public Object deleteSchedule(@PathVariable long courseId){
        return scheduleService.deleteSchedule(courseId);
    }
}
