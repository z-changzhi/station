package com.zy.station.repository;

import com.zy.station.dataobject.Collector;
import com.zy.station.dataobject.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectorRepositoryTest {
    @Autowired
    private CollectorRepository collectorRepository;
    @Test
    public void findByIdIn() {
        Collector byIpIn = collectorRepository.findByStationIdIn(1);
        System.out.println(byIpIn);
    }
    @Test
    public void CollectorTest(){
        List<Collector> all = collectorRepository.findAll();
        for (Collector list : all) {
            System.out.println(list.getId());
        }
    }
}