package com.zy.station.service.Impl;

import com.zy.station.dataobject.Report;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReportServiceImplTest {
    @Autowired
    private ReportServiceImpl reportService;

    @Test
    public void findAll() {
        List<Report> all = reportService.findAll();
        Assert.assertNotEquals(0,all.size());
    }
}