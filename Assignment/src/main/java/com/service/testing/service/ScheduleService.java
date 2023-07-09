package com.service.testing.service;

import com.service.testing.model.Schedule;
import com.service.testing.repository.CourseRepository;
import com.service.testing.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.logging.Logger;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CourseRepository courseRepository;

    Logger LOGGER = Logger.getLogger(String.valueOf(ScheduleService.class));

    public Object getScheduleFromCourseId(long courseId) {
        if(courseRepository.findById(courseId).isPresent()){
            return scheduleRepository.findById(courseId);
        }
        return ("Course ID does not exist");
    }

    public Object addSchedule(long courseId, Schedule schedule) {
        Schedule schedule1 = new Schedule();
        schedule1.setSchedule_id(courseId);
        schedule1.setDate1(String.valueOf(schedule.getDate1()));
        schedule1.setDate1starttime(Time.valueOf(schedule.getDate1starttime().toLocalTime()));
        schedule1.setDate1endtime(Time.valueOf(schedule.getDate1endtime().toLocalTime()));
        schedule1.setDate2(String.valueOf(schedule.getDate2()));
        schedule1.setDate2starttime(Time.valueOf(schedule.getDate2starttime().toLocalTime()));
        schedule1.setDate2endtime(Time.valueOf(schedule.getDate2endtime().toLocalTime()));
        schedule1.setDate3(String.valueOf(schedule.getDate3()));
        schedule1.setDate3starttime(Time.valueOf(schedule.getDate3starttime().toLocalTime()));
        schedule1.setDate3endtime(Time.valueOf(schedule.getDate3endtime().toLocalTime()));
        return scheduleRepository.save(schedule1);
    }

    public Object updateSchedule(long scheduleId, Schedule schedule) {
        Schedule scheduleDB = new Schedule();
        LOGGER.info("start update" + scheduleDB);
        if(scheduleRepository.findById(scheduleId).isPresent()){
            scheduleDB = scheduleRepository.findById(scheduleId).get();
            LOGGER.info("schedule:"+scheduleDB);
            if (scheduleDB.getDate1() != null) {
                scheduleDB.setDate1(schedule.getDate1());
            }
            if (scheduleDB.getDate1starttime() != null) {
                scheduleDB.setDate1starttime(schedule.getDate1starttime());
            }
            if (scheduleDB.getDate1endtime() != null) {
                scheduleDB.setDate1endtime(schedule.getDate1endtime());
            }
            if (scheduleDB.getDate2() != null) {
                scheduleDB.setDate2(schedule.getDate2());
            }
            if (scheduleDB.getDate2starttime() != null) {
                scheduleDB.setDate2starttime(schedule.getDate2starttime());
            }
            if (scheduleDB.getDate2endtime() != null) {
                scheduleDB.setDate2endtime(schedule.getDate2endtime());
            }
            if (scheduleDB.getDate3() != null) {
                scheduleDB.setDate3(schedule.getDate3());
            }
            if (scheduleDB.getDate3starttime() != null) {
                scheduleDB.setDate3starttime(schedule.getDate3starttime());
            }
            if (scheduleDB.getDate3endtime() != null) {
                scheduleDB.setDate3endtime(schedule.getDate3endtime());
            }
            scheduleRepository.save(scheduleDB);
            LOGGER.info("update success: " + scheduleDB);
        }
        return scheduleRepository.save(scheduleDB);
    }

    public Object deleteSchedule(long courseId) {
        if(courseRepository.findById(courseId).isPresent()){
            scheduleRepository.deleteById(courseId);
            return ("Delete Schedule Successful");
        }
        return ("Course Id does not exist");
    }
}
