package com.service.testing.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    private Long schedule_id;
    private String date1;
    private Time date1starttime;
    private Time date1endtime;
    private String date2;
    private Time date2starttime;
    private Time date2endtime;
    private String date3;
    private Time date3starttime;
    private Time date3endtime;
}
