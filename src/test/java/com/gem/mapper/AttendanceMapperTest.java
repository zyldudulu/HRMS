package com.gem.mapper;

import com.gem.entity.Attendance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceMapperTest {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Test
    public  void save()
    {
        Attendance attendance=new Attendance();
        attendance.setUid(new Long(1));
        attendance.setTime(LocalDate.parse("2019-11-11"));
        attendance.setStarttime(LocalTime.parse("11:11:11"));
        attendance.setEndtime(LocalTime.parse("12:12:12"));
        attendance.setStatus("签退");
        attendanceMapper.insert(attendance);
    }

}