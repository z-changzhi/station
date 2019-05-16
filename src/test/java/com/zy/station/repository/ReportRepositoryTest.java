package com.zy.station.repository;

import com.zy.station.dataobject.Report;
import com.zy.station.dataobject.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportRepositoryTest {
    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void findByIdIn() {
        List<Report> all = reportRepository.findAll();
        for (Report list : all) {
            System.out.println(list.getId());
        }
    }

    @Test
    public void Test() {
            List<Integer> devicelist = new ArrayList<>();
            devicelist.add(1);
            List<Report> all = reportRepository.findByDeviceIdIn(devicelist);
            for (Report list : all) {
                System.out.println(list.getId());
            }
    }
}