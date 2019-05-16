package com.zy.station.service.Impl;

import com.zy.station.dataobject.Device;
import lombok.extern.slf4j.Slf4j;
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
public class DeviceServiceImplTest {
    @Autowired
    private DeviceServiceImpl deviceService;

    @Test
    public void findAll() {
        List<Device> all = deviceService.findAll();
        for (Device list : all) {
            System.out.println(list.getId());
        }
    }

    @Test
    public void saveTest(){
        Device device1 = new Device();
        device1.setId(123);
        device1.setStationId(10001);
        Device device = deviceService.saveDevice(device1);
    }
}