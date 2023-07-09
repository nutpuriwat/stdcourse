package com.service.testing.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    private Long course_id;
    private String course_name;
    private LocalDate course_start;
    private Integer seat;

    @OneToMany (cascade = CascadeType.ALL)
//    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
//    private Schedule schedule;
    @JoinTable(
            name = "course_schedule",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedule> schedule = new ArrayList<>();

    public boolean isScheduleOverlap(Course newCourse) {
        for (Schedule schedule : this.getSchedule()) {
            for (Schedule newSchedule : newCourse.getSchedule()) {
                if (schedule.getDate1().equals(newSchedule.getDate1()) && schedule.getDate1starttime().equals(newSchedule.getDate1starttime())
                        && schedule.getDate2().equals(newSchedule.getDate2()) && schedule.getDate2starttime().equals(newSchedule.getDate2starttime())
                        && schedule.getDate3().equals(newSchedule.getDate3()) && schedule.getDate3starttime().equals(newSchedule.getDate3starttime())
                        && isTimeOverlap(schedule, newSchedule)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isTimeOverlap(Schedule schedule, Schedule newSchedule) {
        return false;
    }
}
