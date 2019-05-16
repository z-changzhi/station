package com.zy.station.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CollectorServiceImplTest {
    @Autowired
    private CollectorServiceImpl collectorService;

    @Test
    public void findByStationId() {
        Integer ip = collectorService.findByStationId(2).getIp();
        System.out.println(ip);
    }
}