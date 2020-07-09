package com.gem.mapper;

import com.gem.entity.Leave;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveMapperTest {

    @Autowired
    LeaveMapper leaveMapper;

    @Test
    public void save()
    {
        Leave leave=new Leave();
        leave.setUid(new Long(30));
        leave.setTime(LocalDate.of(2019,1,1));
        leave.setReason("拒绝996");
        leave.setStatus("未审核");
        leaveMapper.insert(leave);
    }

}