package com.gem.mapper;

import com.gem.entity.Information;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InformationMapperTest {

    @Autowired
    InformationMapper informationMapper;

    @Test
    public void add()
    {
        Information information=new Information();
        information.setHead("default.jpg");
        information.setName("zyl");
        information.setGender("男");
        LocalDate localDate = LocalDate.of(2019,1,1);
        information.setBirthday(localDate);
        information.setPhone("177");
        information.setAddress("zibo");
        information.setDepartment("tencent");
        information.setWage(new Long(300000));
        information.setEntry(localDate);
        System.out.println(information);
        int flag=informationMapper.insert(information);
        System.out.println(flag);
    }

    @Test
    public void selectList()
    {
        List<Information> list=informationMapper.selectList(null);
        System.out.println(list);
    }

}