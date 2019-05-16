package com.zy.station.repository;


import com.zy.station.dataobject.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRepositoryTest {
    @Autowired
    private DeviceRepository deviceRepository;
    @Test
    public void findById() {
        List<Device> result = deviceRepository.findAll();
        for (Device list : result) {
            System.out.println(list.getId());
        }
    }

    @Test
    public void Test() {
        List<Integer> devicelist = new ArrayList<>();
        devicelist.add(1);
        List<Device> all = deviceRepository.findByIdIn(devicelist);
        for (Device list : all) {
            System.out.println(list.getId());
        }
    }
    @Test
    public void SaveTest(){
        Device device = new Device();
        device.setId(2);
        device.setTypeId(1);
        device.setStationId(10001);
        device.setRoomId(12);
        device.setPositionId(123);
        device.setStatus(0);
        Device save = deviceRepository.save(device);
        System.out.println(save);
    }
}